package cn.xpms.paas.api.web.construction.task;

import cn.xpms.paas.api.service.business.inter.task.ConstructionTaskServiceInterface;
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
 * @ClassName TaskMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 13:34
 * @Version 1.0
 */
@RestController
@Api(tags = "施工任务删除相关接口")
@RequestMapping("/api/paas/construction/task/")
public class TaskMoldDeleteController {

    /** 施工任务管理service */
    @Autowired
    ConstructionTaskServiceInterface constructionTaskServiceInterface;

    /**
     * @Description: 删除施工任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 13:40
     */
    @ApiOperation("1.删除施工任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("task/{id}")
    public ApiResponseEntity task(@PathVariable("id") Integer id) throws AppException {
        return constructionTaskServiceInterface.deleteTask(id);
    }
}