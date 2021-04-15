package cn.xpms.paas.api.web.project;

import cn.xpms.paas.api.service.business.inter.project.ProjectServiceInterface;
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
 * @ClassName ProjectMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/30 8:51
 * @Version 1.0
 */
@RestController
@Api(tags = "项目信息查询接口")
@RequestMapping("/api/paas/project/")
public class ProjectMoldGetController {

    /** 项目信息service */
    @Autowired
    ProjectServiceInterface projectServiceInterface;

    /**
     * @Description: 根据id查询项目信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 8:58
     */
    @ApiOperation("1.根据id查询项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("project/{id}")
    public ApiResponseEntity project(@PathVariable("id") Integer id) throws AppException {
        return projectServiceInterface.getProject(id);
    }

    @ApiOperation("2.根据项目ID获取项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目projectId", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("project/projectId/{projectId}")
    public ApiResponseEntity projectByProjectId(@PathVariable("projectId") String projectId) throws AppException {
        return projectServiceInterface.getProjectByProjectId(projectId);
    }

    /**
     * @Description: 查询酒店绑定的项目信息
     * @Params: [user_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:00
     */
    @ApiOperation("3.查询酒店绑定的项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotel_id", value = "酒店id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("projects/{hotel_id}")
    public ApiResponseEntity projects(@PathVariable("hotel_id") Integer hotel_id) throws AppException {
        return projectServiceInterface.getHotelBindProjects(hotel_id);
    }
}