package cn.xpms.paas.api.web.scene;

import cn.xpms.paas.api.dao.generator.entity.PaasSceneTemplate;
import cn.xpms.paas.api.service.business.inter.scene.SceneTemplateInterface;
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
 * @ClassName SceneTemplateMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 14:10
 * @Version 1.0
 */
@RestController
@Api(tags = "场景模板")
@RequestMapping("/api/paas/scene/template")
public class SceneTemplateMoldGetController {

    @Autowired
    SceneTemplateInterface sceneTemplateInterface;


    @ApiOperation("1.查询项目场景模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目projectId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "templateName", value = "项目名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sceneName", value = "场景名称", paramType = "query", dataType = "String")
    })
    @GetMapping("templates")
    public ApiResponseEntity getSceneTemplates(PaasSceneTemplate sceneTemplate) throws AppException {
        return sceneTemplateInterface.getSceneTemplates(sceneTemplate);
    }

    @ApiOperation("2.查询场景模板详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("template/detail/{id}")
    public ApiResponseEntity getSceneTemplateDetail(@PathVariable("id") Integer id) throws AppException {
        return sceneTemplateInterface.getSceneTemplateDetail(id);
    }

    @ApiOperation("3.查询场景模板动作列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("template/actions/{id}")
    public ApiResponseEntity getSceneTemplateActions(@PathVariable("id") Integer id) throws AppException {
        return sceneTemplateInterface.getSceneTemplateActions(id);
    }

    @ApiOperation("4.查询场景模板可下发房间列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "场景模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "projectId", value = "项目projectId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("rooms/available/{templateId}/{projectId}")
    public ApiResponseEntity getAvailableRooms(@PathVariable("templateId") Integer templateId,
                                               @PathVariable("projectId") String projectId) throws AppException {
        return sceneTemplateInterface.getAvailableRooms(templateId, projectId);
    }

    @ApiOperation("5.查询场景模板已下发房间列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "场景模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "projectId", value = "项目projectId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("rooms/{templateId}/{projectId}")
    public ApiResponseEntity getTemplateRooms(@PathVariable("templateId") Integer templateId,
                                              @PathVariable("projectId") String projectId) throws AppException {
        return sceneTemplateInterface.getTemplateRooms(templateId, projectId);
    }

}
