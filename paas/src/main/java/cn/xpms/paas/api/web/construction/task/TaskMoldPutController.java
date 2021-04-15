package cn.xpms.paas.api.web.construction.task;

import cn.xpms.paas.api.service.business.inter.task.ConstructionTaskServiceInterface;
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
 * @ClassName TaskMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 11:51
 * @Version 1.0
 */
@RestController
@Api(tags = "施工任务修改相关接口")
@RequestMapping("/api/paas/construction/task/")
public class TaskMoldPutController {

    /** 施工任务管理service */
    @Autowired
    ConstructionTaskServiceInterface constructionTaskServiceInterface;

    /**
     * @Description: 施工任务重开放
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 11:54
     */
    @ApiOperation("1.施工任务重开放")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("reopen/{id}")
    public ApiResponseEntity reopen(@PathVariable("id") Integer id) throws AppException {
        return constructionTaskServiceInterface.reopenTask(id);
    }

    /**
     * @Description: 施工任务验收
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 11:55
     */
    @ApiOperation("2.施工任务验收")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("acceptance/{id}")
    public ApiResponseEntity acceptance(@PathVariable("id") Integer id) throws AppException {
        return constructionTaskServiceInterface.acceptanceTask(id);
    }
}