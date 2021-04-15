package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.AutomationTemplatePo;
import cn.xpms.paas.api.service.business.inter.automation.AutomationTemplateServiceInterface;
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
 * @ClassName AutomationTemplatePutController
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 16:59
 * @Version 1.0
 */
@RestController
@Api(tags = "自动化模板")
@RequestMapping("/api/paas/automation/template")
public class AutomationTemplatePutController {

    @Autowired
    AutomationTemplateServiceInterface automationTemplateService;

    @ApiOperation("1.修改自动化模板")
    @PutMapping(value = "template", produces = "application/json")
    public ApiResponseEntity modifyAutomationTemplate(@RequestBody AutomationTemplatePo templatePo) throws AppException {
        return automationTemplateService.modifyAutomationTemplate(templatePo);
    }
}
