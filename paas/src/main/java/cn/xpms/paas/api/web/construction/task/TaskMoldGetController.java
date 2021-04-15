package cn.xpms.paas.api.web.construction.task;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTask;
import cn.xpms.paas.api.service.business.inter.task.ConstructionTaskServiceInterface;
import cn.xpms.system.framework.beans.annotation.PageQuery;
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
 * @ClassName TaskMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 11:36
 * @Version 1.0
 */
@RestController
@Api(tags = "施工任务查询相关接口")
@RequestMapping("/api/paas/construction/task/")
public class TaskMoldGetController {

    /** 施工任务管理service */
    @Autowired
    ConstructionTaskServiceInterface constructionTaskServiceInterface;

    /**
     * @Description: 查询施工任务列表
     * @Params: [task]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 11:45
     */
    @ApiOperation("1.查询施工任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目唯一标识", required = true, paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "templateId", value = "模板唯一标识", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "status", value = "施工状态", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "worker_id", value = "施工人员唯一标识", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("tasks")
    @PageQuery
    public ApiResponseEntity tasks(PaasConstructionTask task) throws AppException {
        return constructionTaskServiceInterface.getTasks(task);
    }

    /**
     * @Description: 查询施工任务详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 11:47
     */
    @ApiOperation("2.查询施工任务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("task/{id}")
    public ApiResponseEntity task(@PathVariable("id") Integer id) throws AppException {
        return constructionTaskServiceInterface.getTask(id);
    }
}