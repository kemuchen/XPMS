package cn.xpms.paas.api.web.construction.template;

import cn.xpms.paas.api.bean.dto.template.ConstructionTemplatePositionPo;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplate;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateArea;
import cn.xpms.paas.api.service.business.inter.construction.TemplateServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TemplateMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 10:28
 * @Version 1.0
 */
@RestController
@Api(tags = "施工模板修改相关接口")
@RequestMapping("/api/paas/templates/")
public class TemplateMoldPutController {

    /** 施工模板管理service */
    @Autowired
    TemplateServiceInterface templateServiceInterface;

    /**
     * @Description: 修改施工模板
     * @Params: [template]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:37
     */
    @ApiOperation("1.修改施工模板")
    @PutMapping(value = "template", produces = "application/json")
    public ApiResponseEntity template(@RequestBody PaasConstructionTemplate template) throws AppException {
        return templateServiceInterface.modifyTemplate(template);
    }

    /**
     * @Description: 修改施工模板区域
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:39
     */
    @ApiOperation("2.修改施工模板区域")
    @PutMapping(value = "area", produces = "application/json")
    public ApiResponseEntity area(@RequestBody PaasConstructionTemplateArea area) throws AppException {
        return templateServiceInterface.modifyTemplateArea(area);
    }

    /**
     * @Description: 修改施工模板位置
     * @Params: [area]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:39
     */
    @ApiOperation("3.修改施工模板位置")
    @PutMapping(value = "position", produces = "application/json")
    public ApiResponseEntity position(@RequestBody ConstructionTemplatePositionPo positionPo) throws AppException {
        return templateServiceInterface.modifyTemplatePosition(positionPo);
    }
}