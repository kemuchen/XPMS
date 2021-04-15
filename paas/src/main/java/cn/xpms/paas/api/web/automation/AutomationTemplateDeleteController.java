package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.service.business.inter.automation.AutomationTemplateServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AutomationTemplateDeleteController
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 17:01
 * @Version 1.0
 */
@RestController
@Api(tags = "自动化模板")
@RequestMapping("/api/paas/automation/template")
public class AutomationTemplateDeleteController {

    @Autowired
    AutomationTemplateServiceInterface automationTemplateService;

    @ApiOperation("1.删除自动化模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化模板id", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("template/{id}")
    public ApiResponseEntity deleteAutomationTemplate(@PathVariable("id") Integer id) throws AppException {
        return automationTemplateService.deleteAutomationTemplate(id);
    }
}
