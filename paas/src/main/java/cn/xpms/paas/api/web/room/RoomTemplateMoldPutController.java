package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomTemplate;
import cn.xpms.paas.api.service.business.inter.room.RoomTemplateServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RoomTemplateMoldPutController
 * @Desc
 * @Author ycj
 * @Date 2021-01-07 16:59
 * @Version 1.0
 */
@RestController
@Api(tags = "房间模板")
@RequestMapping("/api/paas/room/template")
public class RoomTemplateMoldPutController {

    @Autowired
    RoomTemplateServiceInterface roomTemplateService;

    @ApiOperation("1.修改房间模板信息")
    @PutMapping(value = "template", produces = "application/json")
    public ApiResponseEntity modifyRoomTemplates(@RequestBody PaasRoomTemplate roomTemplate) throws AppException {
        return roomTemplateService.modifyRoomTemplates(roomTemplate);
    }
}
