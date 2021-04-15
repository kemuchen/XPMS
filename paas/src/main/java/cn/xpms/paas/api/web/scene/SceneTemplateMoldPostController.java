package cn.xpms.paas.api.web.scene;

import cn.xpms.paas.api.bean.dto.scene.SceneTemplatePo;
import cn.xpms.paas.api.bean.dto.scene.ScenesByTemplatePo;
import cn.xpms.paas.api.service.business.inter.scene.SceneTemplateInterface;
import cn.xpms.paas.api.service.business.inter.scene.ScenesServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SceneTemplateMoldPostController
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 16:59
 * @Version 1.0
 */
@RestController
@Api(tags = "场景模板")
@RequestMapping("/api/paas/scene/template")
public class SceneTemplateMoldPostController {

    @Autowired
    SceneTemplateInterface sceneTemplateInterface;

    @Autowired
    ScenesServiceInterface scenesServiceInterface;

    @ApiOperation("1.新增场景模板")
    @PostMapping(value = "template", produces = "application/json")
    public ApiResponseEntity addSceneTemplate(@RequestBody SceneTemplatePo sceneTemplatePo) throws AppException {
        return sceneTemplateInterface.addSceneTemplate(sceneTemplatePo);
    }

    @ApiOperation("2.根据场景模板批量创建场景")
    @PostMapping(value = "scenes", produces = "application/json")
    public ApiResponseEntity addScenesByTemplate(@RequestBody ScenesByTemplatePo scenesByTemplatePo) throws AppException {
        return scenesServiceInterface.addScenesByTemplate(scenesByTemplatePo);
    }

}
