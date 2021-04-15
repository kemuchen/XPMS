package cn.xpms.paas.api.web.scene;

import cn.xpms.paas.api.bean.dto.scene.SceneTemplatePo;
import cn.xpms.paas.api.service.business.inter.scene.SceneTemplateInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SceneTemplateMoldPutController
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 17:06
 * @Version 1.0
 */
@RestController
@Api(tags = "场景模板")
@RequestMapping("/api/paas/scene/template")
public class SceneTemplateMoldPutController {

    @Autowired
    SceneTemplateInterface sceneTemplateInterface;

    @ApiOperation("1.修改场景")
    @PutMapping(value = "template", produces = "application/json")
    public ApiResponseEntity modifySceneTemplate(@RequestBody SceneTemplatePo sceneTemplatePo) throws AppException {
        return sceneTemplateInterface.modifySceneTemplate(sceneTemplatePo);
    }

}
