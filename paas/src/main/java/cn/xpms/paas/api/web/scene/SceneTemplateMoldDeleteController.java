package cn.xpms.paas.api.web.scene;

import cn.xpms.paas.api.bean.dto.scene.TemplateScenesPo;
import cn.xpms.paas.api.service.business.inter.scene.SceneTemplateInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SceneTemplateMoldDeleteController
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 17:10
 * @Version 1.0
 */
@RestController
@Api(tags = "场景模板")
@RequestMapping("/api/paas/scene/template")
public class SceneTemplateMoldDeleteController {

    @Autowired
    SceneTemplateInterface sceneTemplateInterface;


    @ApiOperation("1.删除场景模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景模板id", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("template/{id}")
    public ApiResponseEntity deleteSceneTemplate(@PathVariable("id") Integer id) throws AppException {
        return sceneTemplateInterface.deleteSceneTemplate(id);
    }

    @ApiOperation("2.解除场景模板关联(删除场景)")
    @DeleteMapping("templates")
    public ApiResponseEntity deleteTemplateScenes(@RequestBody TemplateScenesPo templateScenesPo) throws AppException {
        return sceneTemplateInterface.deleteTemplateScenes(templateScenesPo);
    }
}
