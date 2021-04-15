package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.CustomAutomationTemplatePo;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationTemplateServiceInterface;
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
 * @ClassName CustomAutomationMoldPutController
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 17:01
 * @Version 1.0
 */
@RestController
@Api(tags = "自定义自动化模板")
@RequestMapping("/api/paas/automation/custom/template")
public class CustomAutomationTempalteMoldPutController {

    @Autowired
    CustomAutomationTemplateServiceInterface customAutomationTemplateService;

    @ApiOperation("1.修改自定义自动化")
    @PutMapping(value = "automation", produces = "application/json")
    public ApiResponseEntity modifyCustomAutomationTemplate(@RequestBody CustomAutomationTemplatePo automationPo) throws AppException {
        return customAutomationTemplateService.modifyCustomAutomationTemplate(automationPo);
    }
}
