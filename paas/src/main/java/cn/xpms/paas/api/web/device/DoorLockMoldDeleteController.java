package cn.xpms.paas.api.web.device;

import cn.xpms.paas.api.service.business.inter.device.DoorLockServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName DoorLockMoldDeleteController
 * @Desc
 * @Author ycj
 * @Date 2021-01-04 16:43
 * @Version 1.0
 */
@RestController
@Api(tags = "门锁")
@RequestMapping("/api/paas/door/lock")
public class DoorLockMoldDeleteController {

    @Autowired
    DoorLockServiceInterface doorLockService;

    @ApiOperation("1.删除临时密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "临时密码记录ID", paramType = "path", dataType = "Integer", defaultValue = "0"),
    })
    @DeleteMapping("tempPassword/{id}")
    public ApiResponseEntity deleteTempPassword(@PathVariable("id") Integer id) throws AppException {
        return doorLockService.deleteTempPassword(id);
    }
}
