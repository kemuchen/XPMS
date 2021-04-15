package cn.xpms.paas.api.service.business.inter.construction;

import cn.xpms.paas.api.dao.generator.entity.PaasInfraredBrand;
import cn.xpms.paas.api.dao.generator.entity.PaasInfraredCategory;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName CategoryServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 9:03
 * @Version 1.0
 */
public interface CategoryServiceInterface {

    /**
     * @Description: 同步施工支持的设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:04
     */
    ApiResponseEntity synchroDeviceCategory() throws AppException;

    /**
     * @Description: 同步品类支持的功能集
     * @Params: [category]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/10 17:11
     */
    ApiResponseEntity synchroDeviceCategoryFunctions(String category) throws AppException;

    /**
     * @Description: 同步施工支持的红外设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:05
     */
    ApiResponseEntity synchroInfraredCategory() throws AppException;

    /**
     * @Description: 同步红外设备品类品牌
     * @Params: [category_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 10:28
     */
    ApiResponseEntity synchroInfraredCategoryBrand(PaasInfraredCategory paasInfraredCategory) throws AppException;

    /**
     * @Description: 同步红外设备品牌支持的遥控索引
     * @Params: [category_id, brand_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 10:29
     */
    ApiResponseEntity synchroInfraredCategoryBrandRemote(PaasInfraredBrand paasInfraredBrand) throws AppException;

    /**
     * @Description: 同步施工拆分设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:06
     */
    ApiResponseEntity synchroSplitDeviceCategory() throws AppException;

    /**
     * @Description: 查询施工支持的设备品类类型
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:36
     */
    ApiResponseEntity getDeviceCategoryType() throws AppException;

    /**
     * @Description: 查询施工支持的设备品类
     * @Params: [type]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:37
     */
    ApiResponseEntity getDeviceCategory(String type) throws AppException;

    /**
     * @Description: 查询施工支持的设备施工品类
     * @Params: [type, category]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:39
     */
    ApiResponseEntity getDeviceConstructionCategory(String type, String category) throws AppException;
    
    /** 
     * @Description: 查询施工支持的拆分设备品类
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:42
     */ 
    ApiResponseEntity getDeviceSplitCategory() throws AppException;

    /**
     * @Description: 查询施工支持的红外设备品类
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:43
     */
    ApiResponseEntity getDeviceInfraredCategory() throws AppException;

    /** 
     * @Description: 查询施工支持的红外设备品牌
     * @Params: [brand]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:48
     */ 
    ApiResponseEntity getDeviceInfraredCategoryBrand(PaasInfraredBrand brand) throws AppException;
    
    /** 
     * @Description: 查询施工支持的红外设备品牌遥控器索引
     * @Params: [category_id, brand_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:50
     */ 
    ApiResponseEntity getDeviceInfraredCategoryBrandRemote(String category_id, String brand_id) throws AppException;
}