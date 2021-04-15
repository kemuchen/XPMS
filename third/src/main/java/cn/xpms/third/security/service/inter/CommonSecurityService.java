package cn.xpms.third.security.service.inter;

import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityUploadInfo;

/**
 * @author ：柯雷
 * @ClassName:：CommonSecurityInterface
 * @Description：
 * @date ：2020/3/26 9:54
 */
public interface CommonSecurityService {

    /**
     * @Description 入住时上传客人信息到公安网
     * @Date 9:57 2020/3/26
     * @Param [orderInfoGuestEntity]
     * @return cn.xpms.framework.common.entity.ApiResponseResultEntity
     **/
    ApiResponseEntity checkInUpload(PublicSecurityUploadInfo uploadInfo) throws AppException;

    /**
     * @Description 退房时上传客人信息到公安网
     * @Date 10:02 2020/3/26
     * @Param [orderInfoGuestEntity]
     * @return cn.xpms.framework.common.entity.ApiResponseResultEntity
     **/
    ApiResponseEntity checkOutUpload(PublicSecurityUploadInfo uploadInfo) throws AppException;
}
