package cn.xpms.paas.api.web.project;

import cn.xpms.paas.api.dao.generator.entity.PaasProjectInfo;
import cn.xpms.paas.api.service.business.inter.project.ProjectServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ProjectMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/30 8:51
 * @Version 1.0
 */
@RestController
@Api(tags = "项目信息修改接口")
@RequestMapping("/api/paas/project/")
public class ProjectMoldPutController {

    /** 项目信息service */
    @Autowired
    ProjectServiceInterface projectServiceInterface;

    /**
     * @Description: 修改项目信息
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:12
     */
    @ApiOperation("1.修改项目信息")
    @PutMapping(value = "project", produces = "application/json")
    public ApiResponseEntity project(@RequestBody PaasProjectInfo paasProjectInfo) throws AppException {
        return projectServiceInterface.modifyProject(paasProjectInfo);
    }

    /**
     * @Description: 项目施工授权
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:15
     */
    @ApiOperation("2.项目施工授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("authorization/{id}")
    public ApiResponseEntity authorization(@PathVariable("id") Integer id) throws AppException {
        return projectServiceInterface.authorizeProject(id);
    }
}