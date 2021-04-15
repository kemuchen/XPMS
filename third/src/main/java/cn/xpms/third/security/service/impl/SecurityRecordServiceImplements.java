package cn.xpms.third.security.service.impl;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.http.HttpUtil;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.third.common.constant.Constants;
import cn.xpms.third.common.error.PublicSecurityErrorCode;
import cn.xpms.third.security.dao.generator.entity.*;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityConfigMapper;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityUploadInfoMapper;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityUploadRecordMapper;
import cn.xpms.third.security.service.inter.SecurityRecordServiceInterface;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PublicSecurityRecordServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/13 10:01
 * @Version 1.0
 */
@Service
public class SecurityRecordServiceImplements implements SecurityRecordServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(SecurityRecordServiceImplements.class);

    /**
     * 公安网接口配置DAO
     */
    @Autowired
    PublicSecurityConfigMapper publicSecurityConfigMapper;

    /**
     * 上传记录DAO
     */
    @Autowired
    PublicSecurityUploadRecordMapper publicSecurityUploadRecordMapper;

    /**
     * 上传信息DAO
     */
    @Autowired
    PublicSecurityUploadInfoMapper publicSecurityUploadInfoMapper;

    /**
     * @Description: 根据上传单号查询上传信息
     * @Params: [orderNo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/14 17:04
     */
    @Override
    public ApiResponseEntity getUpInfosByOrderNo(String orderNo) throws AppException {
        try {
            PublicSecurityUploadInfoExample example = new PublicSecurityUploadInfoExample();
            example.createCriteria().andOrderNoEqualTo(orderNo).andValidEqualTo(SystemConstants.INT_YES);
            List<PublicSecurityUploadInfo> list = publicSecurityUploadInfoMapper.selectByExample(example);
            return new ApiResponseEntity(SysErrorCode.SUCCESS.getError_code(), list);
        } catch (Exception e) {
            logger.error("【SecurityRecordServiceImplements.getUpInfosByOrderNo】查询上传信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存上传信息
     * @Params: [publicSecurityUploadInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/14 17:04
     */
    @Override
    public ApiResponseEntity saveUpInfo(PublicSecurityUploadInfo publicSecurityUploadInfo) throws AppException {
        try {
            if (publicSecurityUploadInfo.getId() == null) {
                publicSecurityUploadInfoMapper.insertSelective(publicSecurityUploadInfo);
            } else {
                publicSecurityUploadInfoMapper.updateByPrimaryKeySelective(publicSecurityUploadInfo);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【SecurityRecordServiceImplements.saveUpInfo】保存上传信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存公安网上传记录
     * @Params: [publicSecurityUploadInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/13 10:06
     */
    @Override
    public ApiResponseEntity saveRecord(String type, PublicSecurityUploadInfo publicSecurityUploadInfo) throws AppException {
        try {
            // 查询是否存在上传记录
            PublicSecurityUploadRecordExample recordExample = new PublicSecurityUploadRecordExample();
            recordExample.createCriteria().andUploadTypeEqualTo(type).
                    andInfoIdEqualTo(publicSecurityUploadInfo.getId()).
                    andValidEqualTo(SystemConstants.INT_YES);
            List<PublicSecurityUploadRecord> recordList = publicSecurityUploadRecordMapper.selectByExample(recordExample);
            if (SysUtil.isEmpty(recordList)) {
                // 没有上传记录则新增
                PublicSecurityUploadRecord record = new PublicSecurityUploadRecord();
                record.setInfoId(publicSecurityUploadInfo.getId());
                record.setUploadType(type);
                if (type.equals(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKIN)) {
                    record.setUploadResult(publicSecurityUploadInfo.getCheckinUpload());
                } else if (type.equals(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT)) {
                    record.setUploadResult(publicSecurityUploadInfo.getCheckoutUpload());
                }
                record.setUploadTimes(1);

                publicSecurityUploadRecordMapper.insertSelective(record);
            } else {
                // 有上传记录则更新
                PublicSecurityUploadRecord record = recordList.get(0);
                record.setUploadTimes(record.getUploadTimes() + 1);
                record.setModifyTime(new Date());
                if (type.equals(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKIN)) {
                    record.setUploadResult(publicSecurityUploadInfo.getCheckinUpload());
                } else if (type.equals(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT)) {
                    record.setUploadResult(publicSecurityUploadInfo.getCheckoutUpload());
                }
                publicSecurityUploadRecordMapper.updateByPrimaryKeySelective(record);
            }
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error("【SecurityRecordServiceImplements.saveRecord】保存上传记录异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取公安网配置
     * @Params: [hotel_code]
     * @return: cn.xpms.third.security.dao.generator.entity.PublicSecurityConfig
     * @Author: 柯雷
     * @Date: 2020/11/13 10:33
     */
    @Override
    public PublicSecurityConfig getPublicSecurityConfig(String hotel_code) throws AppException {
        try {
            // 酒店相关配置
            PublicSecurityConfigExample securityConfigExample = new PublicSecurityConfigExample();
            securityConfigExample.createCriteria().andHotelCodeEqualTo(hotel_code).andValidEqualTo(SystemConstants.INT_YES);
            List<PublicSecurityConfig> securityConfigs = publicSecurityConfigMapper.selectByExample(securityConfigExample);
            // 没有配置
            if (securityConfigs == null || securityConfigs.size() < 1) {
                throw new AppException(PublicSecurityErrorCode.NO_PUBLIC_SECURITY_CONFIG);
            }
            PublicSecurityConfig hotelConfig = securityConfigs.get(0);
            // 接口开关关闭
            if (hotelConfig.getOnOrOff().equals(SystemConstants.INT_NO)) {
                throw new AppException(PublicSecurityErrorCode.PUBLIC_SECURITY_OFF);
            }
            return hotelConfig;
        } catch (AppException e) {
            logger.error("【SecurityRecordServiceImplements.getPublicSecurityConfig】公安网接口配置异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【SecurityRecordServiceImplements.getPublicSecurityConfig】公安网接口配置异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 回调上传结果
     * @Params: [type, publicSecurityUploadInfo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 14:41
     */
    @Override
    public void notifyUploadResult(String type, PublicSecurityUploadInfo publicSecurityUploadInfo) {
        try {
            // 酒店相关配置
            PublicSecurityConfig config = this.getPublicSecurityConfig(publicSecurityUploadInfo.getHotelCode());
            // 当酒店配置回调url不为空时进行回调
            if (!SysUtil.isEmpty(config) && !SysUtil.isEmpty(config.getNorifyUrl())) {
                Map<String, Object> notifyParams = new HashMap<>();
                notifyParams.put("Type", type);
                if (type.equals(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKIN)) {
                    notifyParams.put("Result", publicSecurityUploadInfo.getCheckinUpload());
                } else if (type.equals(Constants.PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT)) {
                    notifyParams.put("Result", publicSecurityUploadInfo.getCheckoutUpload());
                }
                notifyParams.put("ParamExt", publicSecurityUploadInfo.getParamExt());
                notifyParams.put("OrderNo", publicSecurityUploadInfo.getOrderNo());
                HttpUtil.post(config.getNorifyUrl(), JSONObject.toJSONString(notifyParams), null);
            }
        } catch (AppException e) {
            logger.error("【CheckinUploadConsumer.notify】通知回调异常：" + e);
        } catch (Exception e) {
            logger.error("【CheckinUploadConsumer.notify】通知回调异常：" + e);
        }
    }
}
