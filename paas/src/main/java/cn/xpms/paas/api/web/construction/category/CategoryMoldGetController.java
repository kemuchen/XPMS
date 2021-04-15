package cn.xpms.paas.api.web.construction.category;

import cn.xpms.paas.api.dao.generator.entity.PaasInfraredBrand;
import cn.xpms.paas.api.service.business.inter.construction.CategoryServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CategoryMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 9:38
 * @Version 1.0
 */
@RestController
@Api(tags = "设备品类同步相关接口")
@RequestMapping("/api/paas/category/")
public class CategoryMoldGetController {

    /** 设备品类管理service */
    @Autowired
    CategoryServiceInterface categoryServiceInterface;

    /**
     * @Description: 同步施工支持的设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:41
     */
    @ApiOperation("1.同步施工支持的设备品类")
    @GetMapping("synchro/device")
    public ApiResponseEntity device() throws AppException {
        return categoryServiceInterface.synchroDeviceCategory();
    }

    /**
     * @Description: 同步施工支持的红外设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:43
     */
    @ApiOperation("2.同步施工支持的红外设备品类列表")
    @GetMapping("synchro/infrared")
    public ApiResponseEntity infrared() throws AppException {
        return categoryServiceInterface.synchroInfraredCategory();
    }

    /**
     * @Description: 同步施工支持的拆分设备品类列表
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/2 9:45
     */
    @ApiOperation("3.同步施工支持的拆分设备品类列表")
    @GetMapping("synchro/split")
    public ApiResponseEntity split() throws AppException {
        return categoryServiceInterface.synchroSplitDeviceCategory();
    }

    /**
     * @Description: 查询施工支持的设备品类类型
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:33
     */
    @ApiOperation("4.查询施工支持的设备品类类型")
    @GetMapping("device/category/type")
    public ApiResponseEntity deviceCategoryType() throws AppException {
        return categoryServiceInterface.getDeviceCategoryType();
    }

    /**
     * @Description: 查询施工支持的设备品类
     * @Params: [type]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:35
     */
    @ApiOperation("5.查询施工支持的设备品类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "设备品类类型", required = true, paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("device/category/{type}")
    public ApiResponseEntity deviceCategory(@PathVariable("type") String type) throws AppException {
        return categoryServiceInterface.getDeviceCategory(type);
    }

    /**
     * @Description: 查询施工支持的设备施工品类
     * @Params: [type, category]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:39
     */
    @ApiOperation("6.查询施工支持的设备施工品类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "设备品类类型", required = true, paramType = "path", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "category", value = "设备品类", required = true, paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("device/construction/category/{type}/{category}")
    public ApiResponseEntity deviceConstructionCategory(@PathVariable("type") String type,
                                                        @PathVariable("category") String category) throws AppException {
        return categoryServiceInterface.getDeviceConstructionCategory(type, category);
    }

    /**
     * @Description: 查询施工支持的拆分设备品类
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:42
     */
    @ApiOperation("7.查询施工支持的拆分设备品类")
    @GetMapping("device/split/category")
    public ApiResponseEntity deviceSplitCategory() throws AppException {
        return categoryServiceInterface.getDeviceSplitCategory();
    }

    /**
     * @Description: 查询施工支持的红外设备品类
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:43
     */
    @ApiOperation("7.查询施工支持的红外设备品类")
    @GetMapping("device/infrared/category")
    public ApiResponseEntity deviceInfraredCategory() throws AppException {
        return categoryServiceInterface.getDeviceInfraredCategory();
    }

    /**
     * @Description: 查询施工支持的红外设备品牌
     * @Params: [category_id, brand]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:48
     */
    @ApiOperation("8.查询施工支持的红外设备品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category_id", value = "设备品类id", required = true, paramType = "path", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "brandName", value = "品牌名称", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("device/infrared/category/brand/{category_id}")
    public ApiResponseEntity deviceInfraredCategoryBrand(@PathVariable("category_id") String category_id,
                                                         PaasInfraredBrand brand) throws AppException {
        brand.setCategoryId(category_id);
        return categoryServiceInterface.getDeviceInfraredCategoryBrand(brand);
    }

    /**
     * @Description: 查询施工支持的红外设备品牌遥控器索引
     * @Params: [category_id, brand_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 11:50
     */
    @ApiOperation("9.查询施工支持的红外设备品牌遥控器索引")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category_id", value = "设备品类id", required = true, paramType = "path", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "brand_id", value = "品牌id", required = true, paramType = "path", dataType = "String", defaultValue = "0"),
    })
    @GetMapping("device/infrared/category/brand/remote/{category_id}/{brand_id}")
    public ApiResponseEntity deviceInfraredCategoryBrandRemote(@PathVariable("category_id") String category_id,
                                                               @PathVariable("brand_id") String brand_id) throws AppException {
        return categoryServiceInterface.getDeviceInfraredCategoryBrandRemote(category_id, brand_id);
    }

    /**
     * @Description: 同步设备品类功能集
     * @Params: [category]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/10 17:19
     */
    @ApiOperation("10.同步设备品类功能集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "设备品类", required = true, paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("device/category/function/{category}")
    public ApiResponseEntity deviceCategoryFunction(@PathVariable("category") String category) throws AppException {
        return categoryServiceInterface.synchroDeviceCategoryFunctions(category);
    }
}