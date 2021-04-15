package cn.xpms.paas.api.web.construction.template;

import cn.xpms.paas.api.bean.dto.template.ConstructionTemplatePositionPo;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplate;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateArea;
import cn.xpms.paas.api.service.business.inter.construction.TemplateServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TemplateMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 10:28
 * @Version 1.0
 */
@RestController
@Api(tags = "施工模板新增相关接口")
@RequestMapping("/api/paas/templates/")
public class TemplateMoldPostController {

    /**
     * 施工模板管理service
     */
    @Autowired
    TemplateServiceInterface templateServiceInterface;

    /**
     * @Description: 新增施工模板
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:37
     */
    @ApiOperation("1.新增施工模板")
    @PostMapping(value = "template", produces = "application/json")
    public ApiResponseEntity template(@RequestBody PaasConstructionTemplate template) throws AppException {
        return templateServiceInterface.createTemplate(template);
    }

    /**
     * @Description: 新增施工模板区域
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:39
     */
    @ApiOperation("2.新增施工模板区域")
    @PostMapping(value = "area", produces = "application/json")
    public ApiResponseEntity area(@RequestBody PaasConstructionTemplateArea area) throws AppException {
        return templateServiceInterface.createTemplateArea(area);
    }

    /**
     * @Description: 新增施工模板位置
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:39
     */
    @ApiOperation("3.新增施工模板位置")
    @PostMapping(value = "position", produces = "application/json")
    public ApiResponseEntity position(@RequestBody ConstructionTemplatePositionPo positionVo) throws AppException {
        return templateServiceInterface.createTemplatePosition(positionVo);
    }

    @ApiOperation("4.复制施工模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "模板id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0"),
            @ApiImplicitParam(name = "templateName", value = "模板名称", required = true, paramType = "path", dataType = "String", defaultValue = "0")
    })
    @PostMapping(value = "template/copy/{id}/{templateName}", produces = "application/json")
    public ApiResponseEntity copyTemplate(@PathVariable("id") Integer id,
                                          @PathVariable("templateName") String templateName) throws AppException {
        return templateServiceInterface.copyTemplate(id, templateName);
    }
}