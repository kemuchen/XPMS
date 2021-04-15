package cn.xpms.third.security.service.inter;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityConfig;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfo;

/**
 * @ClassName SecurityRecordServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/13 10:02
 * @Version 1.0
 */
public interface SecurityRecordServiceInterface {

    /**
     * 根据单号查询上传记录
     *
     * @param orderNo
     * @return
     * @throws AppException
     */
    ApiResponseEntity getUpInfosByOrderNo(String orderNo) throws AppException;

    /**
     * 保存或修改上传信息
     *
     * @param publicSecurityUploadInfo
     * @return
     * @throws AppException
     */
    ApiResponseEntity saveUpInfo(PublicSecurityUploadInfo publicSecurityUploadInfo) throws AppException;

    /**
     * @Description: 保存上传记录
     * @Params: [publicSecurityUploadInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/13 10:02
     */
    ApiResponseEntity saveRecord(String type, PublicSecurityUploadInfo publicSecurityUploadInfo) throws AppException;

    /**
     * @Description: 获取公安网配置
     * @Params: [hotel_code]
     * @return: cn.xpms.third.security.dao.generator.entity.PublicSecurityConfig
     * @Author: 柯雷
     * @Date: 2020/11/13 10:32
     */
    PublicSecurityConfig getPublicSecurityConfig(String hotel_code) throws AppException;

    /**
     * @Description: 回调上传结果
     * @Params: [type, publicSecurityUploadInfo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 14:40
     */
    void notifyUploadResult(String type, PublicSecurityUploadInfo publicSecurityUploadInfo);
}