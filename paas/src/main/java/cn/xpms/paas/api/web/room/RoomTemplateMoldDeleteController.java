package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.service.business.inter.room.RoomTemplateServiceInterface;
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
 * @ClassName RoomTemplateMoldDeleteController
 * @Desc
 * @Author ycj
 * @Date 2021-01-07 17:01
 * @Version 1.0
 */
@RestController
@Api(tags = "房间模板")
@RequestMapping("/api/paas/room/template")
public class RoomTemplateMoldDeleteController {

    @Autowired
    RoomTemplateServiceInterface roomTemplateService;

    @ApiOperation("1.删除房间模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房间模板id", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("template/{id}")
    public ApiResponseEntity deleteRoomTemplate(@PathVariable("id") Integer id) throws AppException {
        return roomTemplateService.deleteRoomTemplate(id);
    }
}
