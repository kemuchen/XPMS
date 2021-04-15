package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.dao.generator.entity.PaasAutomationTemplate;
import cn.xpms.paas.api.service.business.inter.automation.AutomationTemplateServiceInterface;
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
 * @ClassName AutomationTemplateMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 16:22
 * @Version 1.0
 */
@RestController
@Api(tags = "自动化模板")
@RequestMapping("/api/paas/automation/template")
public class AutomationTemplateMoldGetController {

    @Autowired
    AutomationTemplateServiceInterface automationTemplateService;

    @ApiOperation("1.查询自动化模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目projectId", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "automationName", value = "自动化名称", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("templates")
    public ApiResponseEntity getAutomationTemplates(PaasAutomationTemplate template) throws AppException {
        return automationTemplateService.getAutomationTemplates(template);
    }

    @ApiOperation("2.查询自动化模板详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("template/{id}")
    public ApiResponseEntity getAutomationTemplateDetail(@PathVariable("id") Integer id) throws AppException {
        return automationTemplateService.getAutomationTemplateDetail(id);
    }


    @ApiOperation("3.查询自动化模板下条件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "自动化模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("template/{templateId}/conditions")
    public ApiResponseEntity getAutomationConditions(@PathVariable("templateId") Integer templateId) throws AppException {
        return automationTemplateService.getAutomationConditions(templateId);
    }

    @ApiOperation("4.查询自动化模板下前置条件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "自动化模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("template/{templateId}/preconditions")
    public ApiResponseEntity getAutomationPreconditions(@PathVariable("templateId") Integer templateId) throws AppException {
        return automationTemplateService.getAutomationPreconditions(templateId);
    }

    @ApiOperation("5.查询自动化下模板动作列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "自动化模板记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("template/{templateId}/actions")
    public ApiResponseEntity getAutomationActions(@PathVariable("templateId") Integer templateId) throws AppException {
        return automationTemplateService.getAutomationActions(templateId);
    }
}
