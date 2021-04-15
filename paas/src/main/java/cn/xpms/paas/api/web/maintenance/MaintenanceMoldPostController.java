package cn.xpms.paas.api.web.maintenance;

import cn.xpms.paas.api.bean.dto.task.MaintenanceTaskPo;
import cn.xpms.paas.api.service.business.inter.maintenance.MaintenanceTaskServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @ClassName MaintenanceMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/7 16:28
 * @Version 1.0
 */
@RestController
@Api(tags = "维修任务新增相关接口")
@RequestMapping("/api/paas/maintenance/task/")
public class MaintenanceMoldPostController {

    /** 维修任务管理service */
    @Autowired
    MaintenanceTaskServiceInterface maintenanceTaskServiceInterface;

    /***
     * @Description: 创建维修任务
     * @Params: [taskPo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/7 16:33
     */
    @ApiOperation("1.创建维修任务")
    @PostMapping(value = "task", produces = "application/json")
    public ApiResponseEntity task(@RequestBody MaintenanceTaskPo taskPo) throws AppException {
        return maintenanceTaskServiceInterface.createTask(taskPo);
    }
}
