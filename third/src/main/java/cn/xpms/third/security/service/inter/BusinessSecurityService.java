package cn.xpms.third.security.service.inter;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfo;

/**
 * @ClassName BusinessSecurityService
 * @Desc
 * @Author ycj
 * @Date 2020-11-12 10:55
 * @Version 1.0
 */
public interface BusinessSecurityService {


    /**
     * 入住时上传客人信息到公安网
     *
     * @param uploadInfo
     * @return
     * @throws AppException
     */
    ApiResponseEntity checkInUpload(PublicSecurityUploadInfo uploadInfo) throws AppException;

    /**
     * 退房时上传客人信息到公安网
     *
     * @param uploadInfo
     * @return
     * @throws AppException
     */
    ApiResponseEntity checkOutUpload(PublicSecurityUploadInfo uploadInfo) throws AppException;

    /**
     * 根据单号查询上传记录
     *
     * @param orderNo
     * @return
     * @throws AppException
     */
    ApiResponseEntity getUpInfosByOrderNo(String orderNo) throws AppException;
}
