package cn.xpms.paas.api.web.construction.template;

import cn.xpms.paas.api.service.business.inter.construction.TemplateServiceInterface;
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
 * @ClassName TemplateMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 10:29
 * @Version 1.0
 */
@RestController
@Api(tags = "施工模板删除相关接口")
@RequestMapping("/api/paas/templates/")
public class TemplateMoldDeleteController {

    /** 施工模板管理service */
    @Autowired
    TemplateServiceInterface templateServiceInterface;

    /**
     * @Description: 删除施工模板
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:33
     */
    @ApiOperation("1.删除施工模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "施工模板id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("template/{id}")
    public ApiResponseEntity template(@PathVariable("id") Integer id) throws AppException {
        return templateServiceInterface.deleteTemplate(id);
    }

    /**
     * @Description: 删除施工模板区域
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:34
     */
    @ApiOperation("2.删除施工模板区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "区域id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("area/{id}")
    public ApiResponseEntity area(@PathVariable("id") Integer id) throws AppException {
        return templateServiceInterface.deleteTemplateArea(id);
    }

    /**
     * @Description: 删除施工模板位置
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/3 10:34
     */
    @ApiOperation("3.删除施工模板位置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "位置id", required = true, paramType = "path", dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("position/{id}")
    public ApiResponseEntity position(@PathVariable("id") Integer id) throws AppException {
        return templateServiceInterface.deleteTemplatePosition(id);
    }
}