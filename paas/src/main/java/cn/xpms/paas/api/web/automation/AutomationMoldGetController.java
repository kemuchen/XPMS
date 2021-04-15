package cn.xpms.paas.api.web.automation;

import cn.xpms.paas.api.dao.generator.entity.PaasAutomationInfo;
import cn.xpms.paas.api.service.business.inter.automation.AutomationServiceInterface;
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
 * @ClassName AutomationMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 17:16
 * @Version 1.0
 */
@RestController
@Api(tags = "自动化")
@RequestMapping("/api/paas/automation/")
public class AutomationMoldGetController {

    @Autowired
    AutomationServiceInterface automationServiceInterface;


    @ApiOperation("1.查询房间自动化列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automationInfos")
    public ApiResponseEntity getAutomationInfos(PaasAutomationInfo automationInfo) throws AppException {
        return automationServiceInterface.getAutomationInfos(automationInfo);
    }

    @ApiOperation("2.查询房间自动化详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自动化记录ID", paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("automation/{id}")
    public ApiResponseEntity getAutomationInfo(@PathVariable("id") Integer id) throws AppException {
        return automationServiceInterface.getAutomationInfo(id);
    }


    @ApiOperation("3.查询自动化下条件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "automationId", value = "自动化automationId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automation/{automationId}/conditions")
    public ApiResponseEntity getAutomationConditions(@PathVariable("automationId") String automationId) throws AppException {
        return automationServiceInterface.getAutomationConditions(automationId);
    }

    @ApiOperation("4.查询自动化下前置条件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "automationId", value = "自动化automationId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automation/{automationId}/preconditions")
    public ApiResponseEntity getAutomationPreconditions(@PathVariable("automationId") String automationId) throws AppException {
        return automationServiceInterface.getAutomationPreconditions(automationId);
    }

    @ApiOperation("5.查询自动化下动作列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "automationId", value = "自动化automationId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automation/{automationId}/actions")
    public ApiResponseEntity getAutomationActions(@PathVariable("automationId") String automationId) throws AppException {
        return automationServiceInterface.getAutomationActions(automationId);
    }

    @ApiOperation("6.查询房间下支持自动化条件的设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automation/condition/devices/room/{roomId}")
    public ApiResponseEntity getAutomationConditionDevices(@PathVariable("roomId") String roomId) throws AppException {
        return automationServiceInterface.getAutomationConditionDevices(roomId);
    }

    @ApiOperation("7.查询房间下支持自动化动作的设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automation/action/devices/room/{roomId}")
    public ApiResponseEntity getAutomationActionDevices(@PathVariable("roomId") String roomId) throws AppException {
        return automationServiceInterface.getAutomationActionDevices(roomId);
    }
}
