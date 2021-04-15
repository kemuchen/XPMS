package cn.xpms.third.security.service.impl;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.third.common.error.PublicSecurityErrorCode;
import cn.xpms.third.security.consumer.CheckinUploadConsumer;
import cn.xpms.third.security.consumer.CheckoutUploadConsumer;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfo;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfoExample;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityConfigMapper;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityUploadInfoMapper;
import cn.xpms.third.security.service.inter.BusinessSecurityService;
import cn.xpms.third.security.service.inter.SecurityRecordServiceInterface;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BusinessSecurityServiceImpl
 * @Desc
 * @Author ycj
 * @Date 2020-11-12 10:57
 * @Version 1.0
 */
@Service
public class BusinessSecurityServiceImpl implements BusinessSecurityService {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(BusinessSecurityServiceImpl.class);

    /**
     * 公安网接口配置DAO
     */
    @Autowired
    PublicSecurityConfigMapper publicSecurityConfigMapper;

    /**
     * 公安网上传信息DAO
     */
    @Autowired
    PublicSecurityUploadInfoMapper publicSecurityUploadInfoMapper;

    /**
     * 公安网接口上传入住信息消息队列
     */
    @Autowired
    CheckinUploadConsumer checkinUploadConsumer;

    /**
     * 公安网接口上传退房信息消息队列
     */
    @Autowired
    CheckoutUploadConsumer checkoutUploadConsumer;

    /** 上传记录service */
    @Autowired
    SecurityRecordServiceInterface securityRecordServiceInterface;

    /**
     * 生成订单号  酒店编码+时间戳+4位随机数
     *
     * @return
     */
    private String generateOrderNo() {
        SimpleDateFormat df = new SimpleDateFormat(SystemConstants.DEFAULT_TIMESTAMP_PATTERN);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime()) + RandomStringUtils.randomNumeric(4);
    }

    /**
     * @Description: 入住信息上传公安网
     * @Params: [uploadInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/13 9:21
     */
    @Override
    public ApiResponseEntity checkInUpload(PublicSecurityUploadInfo uploadInfo) throws AppException {
        logger.info("【BusinessSecurityServiceImpl.checkInUpload】入住信息上传公安网:" + uploadInfo);
        try {
            // 校验酒店公安网接口配置
            securityRecordServiceInterface.getPublicSecurityConfig(uploadInfo.getHotelCode());

            // 保存上传信息
            uploadInfo.setOrderNo(generateOrderNo());
            publicSecurityUploadInfoMapper.insertSelective(uploadInfo);
            // 将数据推到消息队列
            checkinUploadConsumer.offer(uploadInfo.getId());
            // 操作成功
            return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), uploadInfo.getOrderNo());
        } catch (AppException e) {
            logger.error("【BusinessSecurityServiceImpl.checkInUpload】入住信息上传公安网异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessSecurityServiceImpl.checkInUpload】入住信息上传公安网异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 退房信息上传公安网
     * @Params: [uploadInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/13 9:54
     */
    @Override
    public ApiResponseEntity checkOutUpload(PublicSecurityUploadInfo uploadInfo) throws AppException {
        logger.info("【BusinessSecurityServiceImpl.checkOutUpload】退房时上传客人信息到公安网:" + uploadInfo);
        try {
            if (uploadInfo == null || StringUtils.isBlank(uploadInfo.getOrderNo())) {
                throw new AppException(PublicSecurityErrorCode.NO_QUERY_ORDER_NO);
            }

            // 校验酒店公安网接口配置
            securityRecordServiceInterface.getPublicSecurityConfig(uploadInfo.getHotelCode());

            // 查询上传信息
            PublicSecurityUploadInfoExample uploadInfoExample = new PublicSecurityUploadInfoExample();
            uploadInfoExample.createCriteria().andOrderNoEqualTo(uploadInfo.getOrderNo());
            List<PublicSecurityUploadInfo> infos = publicSecurityUploadInfoMapper.selectByExample(uploadInfoExample);

            if (SysUtil.isEmpty(infos)) {
                // 无上传信息
                throw new AppException(PublicSecurityErrorCode.NO_UPLOAD_INFO);
            }

            if (uploadInfo.getCheckoutTime() == null) {
                uploadInfo.setCheckoutTime(new Date());
            }
            uploadInfo.setId(infos.get(0).getId());
            publicSecurityUploadInfoMapper.updateByPrimaryKeySelective(uploadInfo);

            // 将数据推到消息队列
            checkoutUploadConsumer.offer(infos.get(0).getId());
            // 操作成功
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【BusinessSecurityServiceImpl.checkOutUpload】退房时上传客人信息到公安网异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【BusinessSecurityServiceImpl.checkOutUpload】退房时上传客人信息到公安网异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 根据单号查询上传记录
     *
     * @param orderNo
     * @return
     * @throws AppException
     */
    @Override
    public ApiResponseEntity getUpInfosByOrderNo(String orderNo) throws AppException {
        return securityRecordServiceInterface.getUpInfosByOrderNo(orderNo);
    }
}