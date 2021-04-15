package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.bean.dto.automation.AutomationPo;
import cn.xpms.paas.api.service.business.inter.automation.AutomationServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AutomationMoldPutController
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 17:26
 * @Version 1.0
 */
@RestController
@Api(tags = "自动化")
@RequestMapping("/api/paas/automation/")
public class AutomationMoldPutController {

    @Autowired
    AutomationServiceInterface automationServiceInterface;

    @ApiOperation("1.修改自动化")
    @PutMapping(value = "automation", produces = "application/json")
    public ApiResponseEntity modifyAutomation(@RequestBody AutomationPo automationPo) throws AppException {
        return automationServiceInterface.modifyAutomation(automationPo);
    }

    @ApiOperation("2.启用自动化")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("automation/enable/{id}")
    public ApiResponseEntity enableAutomation(@PathVariable("id") Integer id) throws AppException {
        return automationServiceInterface.enableAutomation(id);
    }

    @ApiOperation("3.停用自动化")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("automation/disable/{id}")
    public ApiResponseEntity disableAutomation(@PathVariable("id") Integer id) throws AppException {
        return automationServiceInterface.disableAutomation(id);
    }
}
