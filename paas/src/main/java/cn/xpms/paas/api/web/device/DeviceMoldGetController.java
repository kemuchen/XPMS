package cn.xpms.paas.api.web.device;

import cn.xpms.paas.api.bean.dto.device.PaasDeviceQuery;
import cn.xpms.paas.api.service.business.inter.device.DeviceServiceInterface;
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
 * @ClassName DeviceMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2020-12-19 14:57
 * @Version 1.0
 */
@RestController
@Api(tags = "设备")
@RequestMapping("/api/paas/device/")
public class DeviceMoldGetController {

    @Autowired
    DeviceServiceInterface deviceServiceInterface;

    @ApiOperation("1.查询设备功能集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", value = "设备device_id", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("functions/{device_id}")
    public ApiResponseEntity getDeviceFunction(@PathVariable("device_id") String device_id) throws AppException {
        return deviceServiceInterface.getDeviceFunction(device_id);
    }

    @ApiOperation("2.查询设备品类(状态)集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", value = "设备device_id", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("status/{device_id}")
    public ApiResponseEntity getDeviceStatus(@PathVariable("device_id") String device_id) throws AppException {
        return deviceServiceInterface.getDeviceStatus(device_id);
    }

    @ApiOperation("3.查询支持自动化的设备功能集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", value = "设备device_id", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automation/functions/{device_id}")
    public ApiResponseEntity getAutomationDeviceFunction(@PathVariable("device_id") String device_id) throws AppException {
        return deviceServiceInterface.getAutomationDeviceFunction(device_id);
    }

    @ApiOperation("4.查询支持自动化的设备品类(状态)集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", value = "设备device_id", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("automation/status/{device_id}")
    public ApiResponseEntity getAutomationDeviceStatus(@PathVariable("device_id") String device_id) throws AppException {
        return deviceServiceInterface.getAutomationDeviceStatus(device_id);
    }

    @ApiOperation("5.查询设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目project_id", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "roomId", value = "房间room_id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "roomNo", value = "房间room_no", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryCode", value = "设备类型编码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "online", value = "是否在线", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "scene", value = "是否支持场景", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "condition", value = "是否支持自动化条件", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "action", value = "是否支持自动化动作", paramType = "query", dataType = "Integer")
    })
    @GetMapping("devices")
    public ApiResponseEntity getDeviceInfos(PaasDeviceQuery paasDeviceQuery) throws AppException {
        return deviceServiceInterface.getDeviceInfos(paasDeviceQuery);
    }
}
