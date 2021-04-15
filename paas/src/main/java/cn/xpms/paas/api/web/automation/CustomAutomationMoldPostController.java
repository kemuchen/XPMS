package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.CustomAutomationPo;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationServiceInterface;
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
 * @Date 2021-01-12 15:58
 * @Version 1.0
 */
@RestController
@Api(tags = "自定义自动化")
@RequestMapping("/api/paas/automation/custom")
public class CustomAutomationMoldPostController {

    @Autowired
    CustomAutomationServiceInterface customAutomationService;

    @ApiOperation("1.新增自定义自动化")
    @PostMapping(value = "automation", produces = "application/json")
    public ApiResponseEntity addCustomAutomation(@RequestBody CustomAutomationPo automationPo) throws AppException {
        return customAutomationService.addCustomAutomation(automationPo);
    }
}
