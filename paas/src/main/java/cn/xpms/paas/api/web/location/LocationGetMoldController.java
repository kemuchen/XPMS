package cn.xpms.paas.api.web.location;

import cn.xpms.paas.api.dao.generator.entity.PaasLocationInfo;
import cn.xpms.paas.api.service.business.inter.location.LocationServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PaasLocationGetMoldController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 15:40
 * @Version 1.0
 */
@RestController()
@RequestMapping("/api/paas/location/")
@Api(tags = "地理位置信息查询")
public class LocationGetMoldController {

    /** 地理位置信息处理service */
    @Autowired
    LocationServiceInterface locationServiceInterface;

    /**
     * @Description: 获取地理位置信息列表
     * @Params: [parent_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/23 15:43
     */
    @ApiOperation("1.获取地理位置信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parent_id", value = "父地理位置id", required = true, paramType = "query", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "id", value = "父地理位置id", paramType = "query", dataType = "Integer", defaultValue = "0")})
    @GetMapping("location")
    public ApiResponseEntity sendPassword(PaasLocationInfo paasLocationInfo) throws AppException {
        return locationServiceInterface.getLocations(paasLocationInfo);
    }

    /**
     * @Description: 同步地理位置信息
     * @Params: []
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 12:05
     */
    @ApiOperation("2.同步地理位置信息")
    @GetMapping("synchro")
    public ApiResponseEntity synchroLocation() throws AppException {
        return locationServiceInterface.synchroLocation();
    }
}