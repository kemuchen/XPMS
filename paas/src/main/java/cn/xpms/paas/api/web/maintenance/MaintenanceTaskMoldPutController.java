package cn.xpms.paas.api.web.maintenance;

import cn.xpms.paas.api.service.business.inter.maintenance.MaintenanceTaskServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MaintenanceTaskMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/7 16:33
 * @Version 1.0
 */
@RestController
@Api(tags = "维修任务修改相关接口")
@RequestMapping("/api/paas/maintenance/task/")
public class MaintenanceTaskMoldPutController {

    /** 维修任务管理service */
    @Autowired
    MaintenanceTaskServiceInterface maintenanceTaskServiceInterface;

    /**
     * @Description: 维修任务重开放
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/7 16:36
     */
    @ApiOperation("1.维修任务重开放")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("reopen/{id}")
    public ApiResponseEntity reopen(@PathVariable("id") Integer id) throws AppException {
        return maintenanceTaskServiceInterface.reopenTask(id);
    }

    /**
     * @Description: 维修任务验收
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/7 16:37
     */
    @ApiOperation("2.维修任务验收")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("acceptance/{id}")
    public ApiResponseEntity acceptance(@PathVariable("id") Integer id) throws AppException {
        return maintenanceTaskServiceInterface.acceptanceTask(id);
    }
}