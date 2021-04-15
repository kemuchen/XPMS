package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.paas.api.service.business.inter.room.RoomServiceInterface;
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
 * @ClassName RoomMoldPostController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:32
 * @Version 1.0
 */
@RestController
@Api(tags = "房间信息新增接口")
@RequestMapping("/api/paas/room/")
public class RoomMoldPostController {

    /** 房间管理service */
    @Autowired
    RoomServiceInterface roomServiceInterface;

    /**
     * @Description: 新增房间信息
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:28
     */
    @ApiOperation("1.新增房间信息")
    @PostMapping(value = "room", produces = "application/json")
    public ApiResponseEntity room(@RequestBody PaasRoomInfo paasRoomInfo) throws AppException {
        return roomServiceInterface.createRoom(paasRoomInfo);
    }
}