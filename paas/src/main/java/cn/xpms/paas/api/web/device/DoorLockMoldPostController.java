package cn.xpms.paas.api.web.device;

import cn.xpms.paas.api.dao.generator.entity.PaasDoorLockPassword;
import cn.xpms.paas.api.service.business.inter.device.DoorLockServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DoorLockMoldPostController
 * @Desc
 * @Author ycj
 * @Date 2021-01-04 11:27
 * @Version 1.0
 */
@RestController
@Api(tags = "门锁")
@RequestMapping("/api/paas/door/lock")
public class DoorLockMoldPostController {

    @Autowired
    DoorLockServiceInterface doorLockService;

    @ApiOperation("1.创建临时密码")
    @PostMapping(value = "tempPassword", produces = "application/json")
    public ApiResponseEntity createTempPassword(@RequestBody PaasDoorLockPassword password) throws AppException {
        return doorLockService.createTempPassword(password);
    }

    @ApiOperation("2.免密开门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", value = "设备device_id", paramType = "path", dataType = "String", defaultValue = "0")
    })
    @PostMapping("open/free/{device_id}")
    public ApiResponseEntity openWithoutPassword(@PathVariable("device_id") String device_id) throws AppException {
        return doorLockService.openWithoutPassword(device_id);
    }
}
