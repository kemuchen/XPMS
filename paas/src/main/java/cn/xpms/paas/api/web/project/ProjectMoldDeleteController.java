package cn.xpms.paas.api.web.project;

import cn.xpms.paas.api.service.business.inter.project.ProjectServiceInterface;
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
 * @ClassName ProjectMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/30 8:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/paas/project/")
@Api(tags = "项目信息删除接口")
public class ProjectMoldDeleteController {

    /** 项目信息service */
    @Autowired
    ProjectServiceInterface projectServiceInterface;

    /**
     * @Description: 删除项目信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:04
     */
    @ApiOperation("1.删除项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("project/{id}")
    public ApiResponseEntity project(@PathVariable("id") Integer id) throws AppException {
        return projectServiceInterface.deleteProject(id);
    }

    /**
     * @Description: 解除项目授权
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:06
     */
    @ApiOperation("2.解除项目授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("authorization/{id}")
    public ApiResponseEntity authorization(@PathVariable("id") Integer id) throws AppException {
        return projectServiceInterface.unAuthorizeProhect(id);
    }
}