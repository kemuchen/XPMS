package cn.xpms.paas.api.web.space;

import cn.xpms.paas.api.dao.generator.entity.PaasSpaceInfo;
import cn.xpms.paas.api.service.business.inter.space.SpaceServiceInterface;
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
 * @ClassName SpaceMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:30
 * @Version 1.0
 */
@RestController
@Api(tags = "空间相关查询接口")
@RequestMapping("/api/paas/space/")
public class SpaceMoldGetController {

    /** 空间管理service */
    @Autowired
    SpaceServiceInterface spaceServiceInterface;

    /**
     * @Description: 查询空间列表
     * @Params: [paasSpaceInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 8:48
     */
    @ApiOperation("1.查询空间列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentSpaceId", value = "父空间id", paramType = "query", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "projectId", value = "项目唯一标识", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("spaces")
    public ApiResponseEntity sapces(PaasSpaceInfo paasSpaceInfo) throws AppException {
        return spaceServiceInterface.getSpaceInfos(paasSpaceInfo);
    }
}