package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomation;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationServiceInterface;
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
 * @ClassName CustomAutomationMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 15:50
 * @Version 1.0
 */
@RestController
@Api(tags = "自定义自动化")
@RequestMapping("/api/paas/automation/custom")
public class CustomAutomationMoldGetController {

    @Autowired
    CustomAutomationServiceInterface customAutomationService;


    @ApiOperation("1.查询自定义自动化类型")
    @GetMapping("types")
    public ApiResponseEntity getCustomAutomationTypes() throws AppException {
        return customAutomationService.getCustomAutomationTypes();
    }

    @ApiOperation("2.查询自定义自动化列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automationInfos")
    public ApiResponseEntity getCustomAutomation(PaasCustomAutomation automation) throws AppException {
        return customAutomationService.getCustomAutomation(automation);
    }

    @ApiOperation("3.查询自定义自动化详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("automation/{id}")
    public ApiResponseEntity getCustomAutomationDetail(@PathVariable("id") Integer id) throws AppException {
        return customAutomationService.getCustomAutomationDetail(id);
    }


    @ApiOperation("4.查询自定义自动化下条件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "automationId", value = "自定义自动化Id", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("automation/{automationId}/conditions")
    public ApiResponseEntity getCustomAutomationConditions(@PathVariable("automationId") Integer automationId) throws AppException {
        return customAutomationService.getCustomAutomationConditions(automationId);
    }

    @ApiOperation("5.查询自定义自动化下场景列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "automationId", value = "自定义自动化Id", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("automation/{automationId}/scenes")
    public ApiResponseEntity getCustomAutomationScenes(@PathVariable("automationId") Integer automationId) throws AppException {
        return customAutomationService.getCustomAutomationScenes(automationId);
    }

}
