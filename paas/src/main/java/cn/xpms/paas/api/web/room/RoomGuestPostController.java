package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomGuest;
import cn.xpms.paas.api.service.business.inter.room.RoomGuestServiceInterface;
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
 * @ClassName RoomGuestPostController
 * @Desc
 * @Author ycj
 * @Date 2021-01-18 16:49
 * @Version 1.0
 */
@RestController
@Api(tags = "房间客人")
@RequestMapping("/api/paas/room/guest")
public class RoomGuestPostController {

    @Autowired
    RoomGuestServiceInterface roomGuestService;

    @ApiOperation("1.入住")
    @PostMapping(value = "guest", produces = "application/json")
    public ApiResponseEntity checkIn(@RequestBody PaasRoomGuest roomGuest) throws AppException {
        return roomGuestService.checkIn(roomGuest);
    }
}
