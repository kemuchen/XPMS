package cn.xpms.paas.api.web.scene;

import cn.xpms.paas.api.dao.generator.entity.PaasSceneInfo;
import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
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
 * @ClassName ScenesMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 14:10
 * @Version 1.0
 */
@RestController
@Api(tags = "场景")
@RequestMapping("/api/paas/scene/")
public class SceneMoldGetController {

    @Autowired
    ScenesServiceInterface scenesService;


    @ApiOperation("1.查询房间场景列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("scenes")
    public ApiResponseEntity getScenes(PaasSceneInfo paasSceneInfo) throws AppException {
        return scenesService.getScenes(paasSceneInfo);
    }

    @ApiOperation("2.查询房间场景详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("scene/{id}")
    public ApiResponseEntity getScene(@PathVariable("id") Integer id) throws AppException {
        return scenesService.getScene(id);
    }


    @ApiOperation("3.查询房间场景下动作列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景sceneId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("scene/{sceneId}/actions")
    public ApiResponseEntity getSceneActions(@PathVariable("sceneId") String sceneId) throws AppException {
        return scenesService.getSceneActions(sceneId);
    }

    @ApiOperation("4.查询房间下支持场景的设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("scene/devices/room/{roomId}")
    public ApiResponseEntity getRoomSceneDevices(@PathVariable("roomId") String roomId) throws AppException {
        return scenesService.getRoomSceneDevices(roomId);
    }

}
