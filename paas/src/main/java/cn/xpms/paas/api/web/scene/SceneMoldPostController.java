package cn.xpms.paas.api.web.scene;

import cn.xpms.paas.api.bean.dto.scene.ScenePo;
import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SceneMoldPostController
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 16:59
 * @Version 1.0
 */
@RestController
@Api(tags = "场景")
@RequestMapping("/api/paas/scene/")
public class SceneMoldPostController {

    @Autowired
    ScenesServiceInterface scenesServiceInterface;

    @ApiOperation("1.新增场景")
    @PostMapping(value = "scene", produces = "application/json")
    public ApiResponseEntity addScene(@RequestBody ScenePo scenePo) throws AppException {
        return scenesServiceInterface.addScene(scenePo);
    }

    @ApiOperation("2.触发场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "path", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "sceneId", value = "场景sceneId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @PostMapping(value = "trigger/{roomId}/{sceneId}", produces = "application/json")
    public ApiResponseEntity trigger(@PathVariable("roomId") String roomId,
                                     @PathVariable("sceneId") String sceneId) throws AppException {
        return scenesServiceInterface.trigger(roomId, sceneId);
    }

    @ApiOperation("3.根据模板新增场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "path", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "templateId", value = "模板ID", paramType = "path", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "sceneName", value = "场景名称", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @PostMapping(value = "scene/template/{roomId}/{templateId}", produces = "application/json")
    public ApiResponseEntity addSceneByTemplate(@PathVariable("roomId") String roomId,
                                                @PathVariable("templateId") Integer templateId,
                                                String sceneName) throws AppException {
        return scenesServiceInterface.addSceneByTemplate(roomId, templateId, sceneName);
    }
}
