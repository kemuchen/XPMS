package cn.xpms.paas.api.web.maintenance;

import cn.xpms.paas.api.service.business.inter.maintenance.MaintenanceTaskServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MaintenanceTaskMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/7 16:11
 * @Version 1.0
 */
@RestController
@Api(tags = "维修任务删除相关接口")
@RequestMapping("/api/paas/maintenance/task/")
public class MaintenanceTaskMoldDeleteController {

    /** 维修任务管理service */
    @Autowired
    MaintenanceTaskServiceInterface maintenanceTaskServiceInterface;

    /**
     * @Description: 删除维修任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/7 16:27
     */
    @ApiOperation("1.删除维修任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("task/{id}")
    public ApiResponseEntity task(@PathVariable("id") Integer id) throws AppException {
        return maintenanceTaskServiceInterface.deleteTask(id);
    }
}