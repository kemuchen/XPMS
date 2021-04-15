package cn.xpms.paas.api.web.project;

import cn.xpms.paas.api.bean.dto.project.PaasProjectPo;
import cn.xpms.paas.api.dao.generator.entity.PaasProjectInfo;
import cn.xpms.paas.api.service.business.inter.project.ProjectServiceInterface;
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
 * @ClassName ProjectMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/28 8:55
 * @Version 1.0
 */
@RestController
@Api(tags = "项目新增接口")
@RequestMapping("/api/paas/project/")
public class ProjectMoldPostController {

    /**
     * 项目信息service
     */
    @Autowired
    ProjectServiceInterface projectServiceInterface;

    /**
     * @Description: 新增项目信息
     * @Params: [passProjectInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/11/30 9:09
     */
    @ApiOperation("1.新增项目信息")
    @PostMapping(value = "project", produces = "application/json")
    public ApiResponseEntity project(@RequestBody PaasProjectPo paasProjectInfo) throws AppException {
        return projectServiceInterface.createProject(paasProjectInfo);
    }
}