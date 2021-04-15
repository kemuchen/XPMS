package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomTemplate;
import cn.xpms.paas.api.service.business.inter.room.RoomTemplateServiceInterface;
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
 * @ClassName RoomTemplateMoldGetController
 * @Desc
 * @Author ycj
 * @Date 2021-01-07 16:44
 * @Version 1.0
 */
@RestController
@Api(tags = "房间模板")
@RequestMapping("/api/paas/room/template")
public class RoomTemplateMoldGetController {

    @Autowired
    RoomTemplateServiceInterface roomTemplateService;

    @ApiOperation("1.查询房间模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目唯一标识", paramType = "query", dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "templateName", value = "模板名称", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("templates")
    public ApiResponseEntity getRoomTemplates(PaasRoomTemplate roomTemplate) throws AppException {
        return roomTemplateService.getRoomTemplates(roomTemplate);
    }

    @ApiOperation("2.查询房间模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "模板ID", paramType = "path", required = true, dataType = "String", defaultValue = "0")
    })
    @GetMapping("template/{id}")
    public ApiResponseEntity getRoomTemplate(@PathVariable("id") Integer id) throws AppException {
        return roomTemplateService.getRoomTemplate(id);
    }
}
