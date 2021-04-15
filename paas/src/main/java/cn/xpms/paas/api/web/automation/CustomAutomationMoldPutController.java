package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.CustomAutomationPo;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CustomAutomationMoldPutController
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 16:01
 * @Version 1.0
 */
@RestController
@Api(tags = "自定义自动化")
@RequestMapping("/api/paas/automation/custom")
public class CustomAutomationMoldPutController {

    @Autowired
    CustomAutomationServiceInterface customAutomationService;

    @ApiOperation("1.修改自定义自动化")
    @PutMapping(value = "automation", produces = "application/json")
    public ApiResponseEntity modifyCustomAutomation(@RequestBody CustomAutomationPo automationPo) throws AppException {
        return customAutomationService.modifyCustomAutomation(automationPo);
    }
}
