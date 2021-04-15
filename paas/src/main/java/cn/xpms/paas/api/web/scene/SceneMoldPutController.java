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
 * @ClassName SceneMoldPutController
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 17:06
 * @Version 1.0
 */
@RestController
@Api(tags = "场景")
@RequestMapping("/api/paas/scene/")
public class SceneMoldPutController {

    @Autowired
    ScenesServiceInterface scenesServiceInterface;

    @ApiOperation("1.修改场景")
    @PutMapping(value = "scene", produces = "application/json")
    public ApiResponseEntity modifyScene(@RequestBody ScenePo scenePo) throws AppException {
        return scenesServiceInterface.modifyScene(scenePo);
    }

    @ApiOperation("2.同步房间下场景")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @PutMapping("scenes/synchronize/room/{roomId}")
    public ApiResponseEntity synchronizeRoomScenes(@PathVariable("roomId") String roomId) throws AppException {
        return scenesServiceInterface.synchronizeRoomScenes(roomId);
    }

}
