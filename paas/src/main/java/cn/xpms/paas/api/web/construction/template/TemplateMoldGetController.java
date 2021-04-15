package cn.xpms.paas.api.web.construction.template;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplate;
import cn.xpms.paas.api.service.business.inter.construction.TemplateServiceInterface;
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
 * @ClassName TemplateMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 10:28
 * @Version 1.0
 */
@RestController
@Api(tags = "施工模板查询相关接口")
@RequestMapping("/api/paas/templates/")
public class TemplateMoldGetController {

    /** 施工模板管理service */
    @Autowired
    TemplateServiceInterface templateServiceInterface;

    /**
     * @Description: 查询施工模板列表
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:47
     */
    @ApiOperation("1.查询施工模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "模板id", paramType = "query", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "projectId", value = "项目位置标识", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "templateId", value = "施工模板唯一标识", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "templateName", value = "施工模板名称", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("templates")
    public ApiResponseEntity templates(PaasConstructionTemplate template) throws AppException {
        return templateServiceInterface.getTemplates(template);
    }

    /**
     * @Description: 查询施工模板详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:48
     */
    @ApiOperation("2.查询施工模板详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "模板id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("template/{id}")
    public ApiResponseEntity template(@PathVariable("id") Integer id) throws AppException {
        return templateServiceInterface.getTemplate(id);
    }

    /**
     * @Description: 查询施工模板区域
     * @Params: [template_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:49
     */
    @ApiOperation("3.查询施工模板区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "template_id", value = "模板唯一标识", required = true, paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("area/{template_id}")
    public ApiResponseEntity area(@PathVariable("template_id") String template_id) throws AppException {
        return templateServiceInterface.getTemplateAreas(template_id);
    }

    /**
     * @Description: 查询施工模板设备安装位置
     * @Params: [area_id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:50
     */
    @ApiOperation("4.查询施工模板设备安装位置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area_id", value = "区域唯一标识", required = true, paramType = "path", dataType = "String", defaultValue = "0")
    })
    @GetMapping("position/{area_id}")
    public ApiResponseEntity position(@PathVariable("area_id") String area_id) throws AppException {
        return templateServiceInterface.getTemplatePositions(area_id);
    }
}