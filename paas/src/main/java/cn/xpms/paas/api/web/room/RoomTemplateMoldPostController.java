package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.bean.dto.room.TemplatePo;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomTemplate;
import cn.xpms.paas.api.service.business.inter.room.RoomTemplateServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoomTemplateMoldPostController
 * @Desc
 * @Author ycj
 * @Date 2021-01-07 16:55
 * @Version 1.0
 */
@RestController
@Api(tags = "房间模板")
@RequestMapping("/api/paas/room/template")
public class RoomTemplateMoldPostController {

    @Autowired
    RoomTemplateServiceInterface roomTemplateService;

    @ApiOperation("1.新增房间模板信息")
    @PostMapping(value = "template", produces = "application/json")
    public ApiResponseEntity addRoomTemplates(@RequestBody PaasRoomTemplate roomTemplate) throws AppException {
        return roomTemplateService.addRoomTemplates(roomTemplate);
    }

    @ApiOperation("2.根据房间模板下发房间场景以及自动化")
    @PostMapping(value = "sceneAndAutomation", produces = "application/json")
    public ApiResponseEntity createSceneAndAutomationByTemplate(@RequestBody TemplatePo templatePo) throws AppException {
        return roomTemplateService.createSceneAndAutomationByTemplate(templatePo.getTemplate_id(), templatePo.getRoom_ids());
    }
}
