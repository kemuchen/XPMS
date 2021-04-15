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

import javax.ws.rs.Path;
import java.util.Date;

/**
 * @ClassName DoorLockMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2021-01-04 16:34
 * @Version 1.0
 */
@RestController
@Api(tags = "门锁")
@RequestMapping("/api/paas/door/lock")
public class DoorLockMoldGetController {

    @Autowired
    DoorLockServiceInterface doorLockService;

    @ApiOperation("1.查询开门记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", value = "设备device_id", paramType = "path", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "start_time", value = "开始时间", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "end_time", value = "结束时间", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "page_no", value = "页码", paramType = "query", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "page_size", value = "页条数", paramType = "query", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("openLogs/{device_id}")
    public ApiResponseEntity getOpenLogs(@PathVariable("device_id") String device_id,
                                         Date start_time, Date end_time, Integer page_no, Integer page_size) throws AppException {
        return doorLockService.getOpenLogs(device_id, page_no, page_size, start_time, end_time);
    }
}
