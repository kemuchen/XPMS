package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.AutomationByTemplatePo;
import cn.xpms.paas.api.bean.dto.automation.AutomationTemplatePo;
import cn.xpms.paas.api.service.business.inter.automation.AutomationServiceInterface;
import cn.xpms.paas.api.service.business.inter.automation.AutomationTemplateServiceInterface;
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
 * @ClassName AutomationTemplatePostController
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 16:59
 * @Version 1.0
 */
@RestController
@Api(tags = "自动化模板")
@RequestMapping("/api/paas/automation/template")
public class AutomationTemplatePostController {

    @Autowired
    AutomationTemplateServiceInterface automationTemplateService;

    @Autowired
    AutomationServiceInterface automationService;

    @ApiOperation("1.新增自动化模板")
    @PostMapping(value = "template", produces = "application/json")
    public ApiResponseEntity addAutomationTemplate(@RequestBody AutomationTemplatePo templatePo) throws AppException {
        return automationTemplateService.addAutomationTemplate(templatePo);
    }

    @ApiOperation("2.根据场景模板批量创建自动化")
    @PostMapping(value = "automations", produces = "application/json")
    public ApiResponseEntity addScenesByTemplate(@RequestBody AutomationByTemplatePo scenesByTemplatePo) throws AppException {
        return automationService.addAutomationsByTemplate(scenesByTemplatePo);
    }
}
