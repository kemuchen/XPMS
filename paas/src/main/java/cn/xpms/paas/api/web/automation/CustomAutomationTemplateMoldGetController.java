package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationTemplate;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationTemplateServiceInterface;
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
 * @ClassName CustomAutomationMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 16:50
 * @Version 1.0
 */
@RestController
@Api(tags = "自定义自动化模板")
@RequestMapping("/api/paas/automation/custom/template")
public class CustomAutomationTemplateMoldGetController {

    @Autowired
    CustomAutomationTemplateServiceInterface customAutomationTemplateService;


    @ApiOperation("1.查询自定义自动化模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目projectId", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("templates")
    public ApiResponseEntity getCustomAutomationTemplates(PaasCustomAutomationTemplate automation) throws AppException {
        return customAutomationTemplateService.getCustomAutomationTemplates(automation);
    }

    @ApiOperation("2.查询自定义自动化详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("automation/{id}")
    public ApiResponseEntity getCustomAutomationTemplateDetail(@PathVariable("id") Integer id) throws AppException {
        return customAutomationTemplateService.getCustomAutomationTemplateDetail(id);
    }


    @ApiOperation("3.查询自定义自动化下条件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "automationId", value = "自定义自动化Id", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("automation/{automationId}/conditions")
    public ApiResponseEntity getCustomAutomationConditions(@PathVariable("automationId") Integer automationId) throws AppException {
        return customAutomationTemplateService.getCustomAutomationTemplateConditions(automationId);
    }

    @ApiOperation("4.查询自定义自动化下场景列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "automationId", value = "自定义自动化Id", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("automation/{automationId}/scenes")
    public ApiResponseEntity getCustomAutomationTemplateScenes(@PathVariable("automationId") Integer automationId) throws AppException {
        return customAutomationTemplateService.getCustomAutomationTemplateScenes(automationId);
    }

}
