package cn.xpms.paas.api.service.business.inter.location;

import cn.xpms.paas.api.dao.generator.entity.PaasLocationInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName PaasLocationServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 15:28
 * @Version 1.0
 */
public interface LocationServiceInterface {

    /**
     * @Description: 获取地理位置信息列表
     * @Params: [parent_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/23 15:29
     */
    ApiResponseEntity getLocations(PaasLocationInfo locationInfo) throws AppException;

    /**
     * @Description: 同步地理位置信息
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 12:01
     */
    ApiResponseEntity synchroLocation() throws AppException;
}