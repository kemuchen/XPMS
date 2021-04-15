package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationTemplateServiceInterface;
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
 * @ClassName CustomAutomationMoldDeleteController
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 16:06
 * @Version 1.0
 */
@RestController
@Api(tags = "自定义自动化模板")
@RequestMapping("/api/paas/automation/custom/template")
public class CustomAutomationTemplateMoldDeleteController {

    @Autowired
    CustomAutomationTemplateServiceInterface customAutomationTemplateService;

    @ApiOperation("1.删除自定义自动化")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化id", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("automation/{id}")
    public ApiResponseEntity deleteCustomAutomationTemplate(@PathVariable("id") Integer id) throws AppException {
        return customAutomationTemplateService.deleteCustomAutomationTemplate(id);
    }
}
