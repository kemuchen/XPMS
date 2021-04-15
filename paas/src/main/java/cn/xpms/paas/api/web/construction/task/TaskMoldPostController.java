package cn.xpms.paas.api.web.construction.task;

import cn.xpms.paas.api.bean.dto.task.ConstructionTemplateTaskPo;
import cn.xpms.paas.api.service.business.inter.task.ConstructionTaskServiceInterface;
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
 * @ClassName TaskMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 11:47
 * @Version 1.0
 */
@RestController
@Api(tags = "施工任务新增相关接口")
@RequestMapping("/api/paas/construction/task/")
public class TaskMoldPostController {

    /** 施工任务管理service */
    @Autowired
    ConstructionTaskServiceInterface constructionTaskServiceInterface;

    /**
     * @Description: 创建施工任务
     * @Params: [taskPo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 11:50
     */
    @ApiOperation("1.创建施工任务")
    @PostMapping(value = "task", produces = "application/json")
    public ApiResponseEntity task(@RequestBody ConstructionTemplateTaskPo taskPo) throws AppException {
        return constructionTaskServiceInterface.createTemplateTask(taskPo);
    }
}