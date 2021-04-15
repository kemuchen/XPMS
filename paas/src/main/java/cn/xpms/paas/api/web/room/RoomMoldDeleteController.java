package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.service.business.inter.room.RoomServiceInterface;
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
 * @ClassName RoomMoldDeleteController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:32
 * @Version 1.0
 */
@RestController
@Api(tags = "房间信息删除接口")
@RequestMapping("/api/paas/room/")
public class RoomMoldDeleteController {

    /** 房间管理service */
    @Autowired
    RoomServiceInterface roomServiceInterface;

    /**
     * @Description: 删除房间
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:04
     */
    @ApiOperation("1.删除房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房间id", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @DeleteMapping("room/{id}")
    public ApiResponseEntity room(@PathVariable("id") Integer id) throws AppException {
        return roomServiceInterface.deleteRoom(id);
    }
}