package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.CustomAutomationByTemplatePo;
import cn.xpms.paas.api.bean.dto.automation.CustomAutomationTemplatePo;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationServiceInterface;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationTemplateServiceInterface;
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
 * @ClassName CustomAutomationMoldPostController
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 16:58
 * @Version 1.0
 */
@RestController
@Api(tags = "自定义自动化模板")
@RequestMapping("/api/paas/automation/custom/template")
public class CustomAutomationTemplateMoldPostController {

    @Autowired
    CustomAutomationTemplateServiceInterface customAutomationTemplateService;
    @Autowired
    CustomAutomationServiceInterface customAutomationService;

    @ApiOperation("1.新增自定义自动化")
    @PostMapping(value = "automation", produces = "application/json")
    public ApiResponseEntity addCustomAutomationTemplate(@RequestBody CustomAutomationTemplatePo automationPo) throws AppException {
        return customAutomationTemplateService.addCustomAutomationTemplate(automationPo);
    }

    @ApiOperation("2.根据模板批量创建自定义自动化")
    @PostMapping(value = "automations", produces = "application/json")
    public ApiResponseEntity addScenesByTemplate(@RequestBody CustomAutomationByTemplatePo automationByTemplatePo) throws AppException {
        return customAutomationService.addAutomationByTemplate(automationByTemplatePo);
    }
}
