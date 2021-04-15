package cn.xpms.paas.api.service.business.inter.space;

import cn.xpms.paas.api.dao.generator.entity.PaasSpaceInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName SpaceServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:33
 * @Version 1.0
 */
public interface SpaceServiceInterface {

    /**
     * @Description: 新增空间
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 8:34
     */
    ApiResponseEntity createSapce(PaasSpaceInfo paasSpaceInfo) throws AppException;

    /**
     * @Description: 修改空间
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 8:34
     */
    ApiResponseEntity modifySpace(PaasSpaceInfo paasSpaceInfo) throws AppException;

    /**
     * @Description: 删除空间
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 8:35
     */
    ApiResponseEntity deleteSpace(Integer id) throws AppException;

    /**
     * @Description: 查询空间列表
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 8:36
     */
    ApiResponseEntity getSpaceInfos(PaasSpaceInfo paasSpaceInfo) throws AppException;
}