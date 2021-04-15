package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.paas.api.service.business.inter.device.DeviceServiceInterface;
import cn.xpms.paas.api.service.business.inter.room.RoomServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RoomMoldPutController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:32
 * @Version 1.0
 */
@RestController
@Api(tags = "房间信息修改接口")
@RequestMapping("/api/paas/room/")
public class RoomMoldPutController {

    /**
     * 房间管理service
     */
    @Autowired
    RoomServiceInterface roomServiceInterface;

    @Autowired
    DeviceServiceInterface deviceServiceInterface;

    /**
     * @Description: 修改房间信息
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:31
     */
    @ApiOperation("1.修改房间信息")
    @PutMapping("room")
    public ApiResponseEntity room(@RequestBody PaasRoomInfo paasRoomInfo) throws AppException {
        return roomServiceInterface.modifyRoom(paasRoomInfo);
    }

    @ApiOperation("2.同步房间设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomId", value = "房间roomId", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @PutMapping("devices/synchronize/{roomId}")
    public ApiResponseEntity synchronizeRoomDevice(@PathVariable("roomId") String roomId) throws AppException {
        deviceServiceInterface.synchroRoomDevice(roomId);
        return new ApiResponseEntity(SysErrorCode.SUCCESS);
    }
}