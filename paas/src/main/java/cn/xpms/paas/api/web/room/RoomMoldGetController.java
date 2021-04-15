package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.paas.api.service.business.inter.room.RoomServiceInterface;
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
 * @ClassName RoomMoldGetController
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:31
 * @Version 1.0
 */
@RestController
@Api(tags = "获取房间信息")
@RequestMapping("/api/paas/room/")
public class RoomMoldGetController {

    /**
     * 房间管理service
     */
    @Autowired
    RoomServiceInterface roomServiceInterface;

    /**
     * @Description: 查询房间列表
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:15
     */
    @ApiOperation("1.查询房间列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目唯一标识", paramType = "query", required = true, dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "spaceId", value = "空间唯一标识", paramType = "query", dataType = "String", defaultValue = "0")
    })
    @GetMapping("rooms")
    public ApiResponseEntity rooms(PaasRoomInfo paasRoomInfo) throws AppException {
        return roomServiceInterface.getRooms(paasRoomInfo);
    }

    /**
     * @Description: 查询房间详细信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:24
     */
    @ApiOperation("2.查询房间详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房间id", paramType = "path", required = true, dataType = "Integer", defaultValue = "0")
    })
    @GetMapping("room/{id}")
    public ApiResponseEntity room(@PathVariable("id") Integer id) throws AppException {
        return roomServiceInterface.getRoomDetail(id);
    }

    /**
     * @Description: 查询房态
     * @Params: PaasRoomInfo
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-12 10:29
     */
    @ApiOperation("3.查询房态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目ID", paramType = "query", required = false, dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "roomId", value = "房间ID", paramType = "query", required = false, dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "spaceId", value = "空间ID", paramType = "query", required = false, dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "roomNo", value = "房号", paramType = "query", required = false, dataType = "String", defaultValue = "0"),
            @ApiImplicitParam(name = "constructionStatus", value = "施工状态", paramType = "query", required = false, dataType = "String", defaultValue = "0")
    })
    @GetMapping("rooms/status")
    public ApiResponseEntity roomsStatus(PaasRoomInfo roomInfo) throws AppException {
        return roomServiceInterface.getRoomsStatus(roomInfo);
    }

}