package cn.xpms.third.security.service.impl;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.DateUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.third.common.cache.PublicSecuritySysConfigUtil;
import cn.xpms.third.common.constant.Constants;
import cn.xpms.third.common.error.PublicSecurityErrorCode;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityConfig;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfo;
import cn.xpms.third.security.service.inter.CommonSecurityService;
import cn.xpms.third.security.service.inter.SecurityRecordServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;

/**
 * @ClassName CommonSecurityServiceImpl
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/12 11:25
 * @Version 1.0
 */
@org.springframework.stereotype.Service
public class CommonSecurityServiceImpl implements CommonSecurityService {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(CommonSecurityServiceImpl.class);

    /** 上传记录service */
    @Autowired
    SecurityRecordServiceInterface securityRecordServiceInterface;

    /** 公安网请求地址 */
    String security_url;

    /** 公安网命名空间 */
    String namespace;

    public void init() throws AppException {
        try {
            if (security_url == null) {
                security_url = PublicSecuritySysConfigUtil.
                        getPublicSecuritySysConfigValue(Constants.PUBLIC_SECURITY_URL);
            }
            if (namespace == null) {
                namespace = PublicSecuritySysConfigUtil.
                        getPublicSecuritySysConfigValue(Constants.PUBLIC_SECURITY_NAMESPACE);
            }

            // 不能为空
            if (SysUtil.isEmpty(security_url) || SysUtil.isEmpty(namespace)) {
                throw new AppException(PublicSecurityErrorCode.UPLOAD_SYSCONFIG_ERROR);
            }
        } catch (AppException e) {
            logger.error("【CommonSecurityServiceImpl.init】初始化异常：" + e);
            throw e;
        }
    }

    /**
     * @Description: 初始化请求分发器
     * @Params: []
     * @return: javax.xml.ws.Dispatch<javax.xml.soap.SOAPMessage>
     * @Author: 柯雷
     * @Date: 2020/11/13 15:34
     */
    private Dispatch<SOAPMessage> initDispatch() throws AppException {
        try {
            // 初始化
            init();

            //1.创建服务
            URL url = new URL(security_url);
            QName qName = new QName(namespace, Constants.PUBLIC_SECURITY_LOCAL_SERVICE);
            Service service = Service.create(url, qName);

            //2.创建Dispatch
            Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(namespace,
                            Constants.PUBLIC_SECURITY_LOCAL_PORT), SOAPMessage.class, Service.Mode.MESSAGE);
            return dispatch;
        } catch (AppException e) {
            logger.error("【CommonSecurityServiceImpl.initDispatch】初始化请求分发器异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CommonSecurityServiceImpl.initDispatch】初始化请求分发器异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 进行上传
     * @Params: []
     * @return: javax.xml.soap.SOAPElement
     * @Author: 柯雷
     * @Date: 2020/11/13 15:49
     */
    private String doUpload(String type, PublicSecurityUploadInfo uploadInfo) throws AppException {
        try {
            // 1.初始化请求分发器
            Dispatch<SOAPMessage> dispatch = initDispatch();

            //2.创建 SOAPMessage以及请求头请求体
            SOAPMessage msg = MessageFactory.newInstance().createMessage();
            SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();

            // 请求体
            SOAPBody soapBody = envelope.getBody();
            // 请求头
            SOAPHeader soapHeader = envelope.getHeader();
            if (soapHeader == null) {
                soapHeader = envelope.addHeader();
            }

            // 3.设置请求体和请求头数据
            SOAPElement body;
            SOAPElement header;
            if (Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKIN.equals(type)) {
                // 入住上传
                body = soapBody.addChildElement(new QName(namespace,
                        Constants.PUBLIC_SECURITY_CHECKIN_LOCAL_PART, Constants.PUBLIC_SECURITY_PREFIX));
                header = soapHeader.addChildElement(new QName(namespace,
                        Constants.PUBLIC_SECURITY_CHECKIN_LOCAL_PART, Constants.PUBLIC_SECURITY_PREFIX));
                // 设置入住请求体数据
                this.setCheckinBodyElement(body, uploadInfo);
            } else {
                // 退房上传
                body = soapBody.addChildElement(new QName(namespace,
                        Constants.PUBLIC_SECURITY_CHECKOUT_LOCAL_PART, Constants.PUBLIC_SECURITY_PREFIX));
                header = soapHeader.addChildElement(new QName(namespace,
                        Constants.PUBLIC_SECURITY_CHECKOUT_LOCAL_PART, Constants.PUBLIC_SECURITY_PREFIX));
                // 设置退房请求体数据
                this.setCheckoutBodyElement(body, uploadInfo);
            }

            // 获取酒店公安网接口配置并设置请求头数据
            PublicSecurityConfig config = securityRecordServiceInterface
                    .getPublicSecurityConfig(uploadInfo.getHotelCode());
            header.addChildElement("username").setValue(config.getUsername());
            header.addChildElement("password").setValue(config.getPassword());

            // 4.发起请求并返回数据
            SOAPMessage responseMsg = dispatch.invoke(msg);
            Document doc = responseMsg.getSOAPPart().getEnvelope().getOwnerDocument();
            StringWriter output = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(output));
            return output.toString();
        } catch (AppException e) {
            logger.error("【CommonSecurityServiceImpl.initHeaderSOAPElement】获取请求元素异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CommonSecurityServiceImpl.initHeaderSOAPElement】获取请求元素异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 设置入住上传请求体数据
     * @Params: [body, uploadInfo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 16:30
     */
    private void setCheckinBodyElement(SOAPElement body, PublicSecurityUploadInfo uploadInfo) throws AppException {
        try {
            body.addChildElement("arg0").setValue(uploadInfo.getName());
            body.addChildElement("arg1").setValue(uploadInfo.getSex());
            body.addChildElement("arg2").setValue(SysUtil.nvl(uploadInfo.getNation(),
                    Constants.PUBLIC_SECURITY_DEFAULT_NATION));
            body.addChildElement("arg3").setValue(DateUtil.formatDate(uploadInfo.getBirthday(),
                    SystemConstants.DEFAULT_DATE_PATTERN));
            body.addChildElement("arg4").setValue(uploadInfo.getCredentialValidStart()
                    + "-" + uploadInfo.getCredentialValidEnd());    // 证件有效期
            body.addChildElement("arg5").setValue(SysUtil.nvl(uploadInfo.getCardType(),
                    Constants.PUBLIC_SECURITY_DEFAULT_CARD_TYPE));
            body.addChildElement("arg6").setValue(uploadInfo.getCredentialNo());
            body.addChildElement("arg9").setValue(SysUtil.nvl(uploadInfo.getAddress()));
            body.addChildElement("arg10").setValue(SysUtil.nvl(uploadInfo.getPhone()));
            body.addChildElement("arg11").setValue(uploadInfo.getHotelCode());
            body.addChildElement("arg12").setValue(uploadInfo.getRoomNo());
            body.addChildElement("arg13").setValue(SysUtil.imageToBase64(uploadInfo.getCredentialImage()));
            body.addChildElement("arg14").setValue(SysUtil.imageToBase64(uploadInfo.getFaceImage()));
            body.addChildElement("arg15").setValue(SysUtil.nvl(uploadInfo.getVerifyResult(),
                    Constants.PUBLIC_SECURITY_VERIFY_RESULT));
            if (SysUtil.isEmpty(uploadInfo.getVerifyScore())) {
                body.addChildElement("arg16").setValue(Constants.PUBLIC_SECURITY_VERIFY_SCORE);
            } else {
                body.addChildElement("arg16").setValue(new BigDecimal(uploadInfo.getVerifyScore())
                        .multiply(new BigDecimal(100)).intValue()
                        + SystemConstants.BLANK_STRING);
            }
            body.addChildElement("arg17").setValue(DateUtil.formatDate(uploadInfo.getCheckinTime(),
                    SystemConstants.DEFAULT_DATE_TIME_PATTERN));
        } catch (Exception e) {
            logger.error("【CommonSecurityServiceImpl.setCheckinBodyElement】设置入住上传请求体数据异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 设置退房上传请求体数据
     * @Params: [body, uploadInfo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 16:30
     */
    private void setCheckoutBodyElement(SOAPElement body, PublicSecurityUploadInfo uploadInfo) throws AppException {
        try {
            body.addChildElement("arg0").setValue(uploadInfo.getCredentialNo());    // 身份证号
            body.addChildElement("arg1").setValue(DateUtil.formatDate(uploadInfo.getCheckinTime(),
                    SystemConstants.DEFAULT_DATE_TIME_PATTERN));       // 登记时间
            body.addChildElement("arg2").setValue(DateUtil.formatDate(uploadInfo.getCheckoutTime(),
                    SystemConstants.DEFAULT_DATE_TIME_PATTERN));      // 退房时间
            body.addChildElement("arg3").setValue(uploadInfo.getHotelCode());    // 酒店编号
        } catch (Exception e) {
            logger.error("【CommonSecurityServiceImpl.setCheckoutBodyElement】设置退房上传请求体数据异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 入住时上传客人信息到公安网
     * @param uploadInfo
     * @return
     * @throws AppException
     */
    @Override
    public ApiResponseEntity checkInUpload(PublicSecurityUploadInfo uploadInfo) throws AppException {
        logger.info("【CommonSecurityImplements.checkInUpload】入住时上传客人信息到公安网:" + uploadInfo);
        try {
            // 调用统一上传
            String response = this.doUpload(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKIN, uploadInfo);
            logger.error(response);

            if (response.indexOf(Constants.PUBLIC_SECURITY_CHECKIN_SUCCESS) < 0) {
                logger.error(uploadInfo.getName() + "入住上传公安网失败，酒店：" + uploadInfo.getHotelCode());
                throw new AppException(PublicSecurityErrorCode.CHECKIN_UPLOAD_FAIL);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【CommonSecurityImplements.checkInUpload】入住时上传客人信息到公安网:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CommonSecurityImplements.checkInUpload】入住时上传客人信息到公安网:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 退房时上传客人信息到公安网
     * @param uploadInfo
     * @return
     * @throws AppException
     */
    @Override
    public ApiResponseEntity checkOutUpload(PublicSecurityUploadInfo uploadInfo) throws AppException {
        logger.info("【CommonSecurityImplements.checkOutUpload】退房时上传客人信息到公安网:" + uploadInfo);
        try {
            // 调用统一上传
            String response = this.doUpload(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT, uploadInfo);
            logger.error(response);

            if (response.indexOf(Constants.PUBLIC_SECURITY_CHECKOUT_SUCCESS) < 0) {
                logger.error(uploadInfo.getName() + "退房上传公安网失败，酒店：" + uploadInfo.getHotelCode());
                throw new AppException(PublicSecurityErrorCode.CHECKOUT_UPLOAD_FAIL);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【CommonSecurityImplements.checkOutUpload】退房时上传客人信息到公安网:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【CommonSecurityImplements.checkOutUpload】退房时上传客人信息到公安网");
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}