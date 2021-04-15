package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.service.business.inter.room.RoomGuestServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoomGuestPutController
 * @Desc
 * @Author ycj
 * @Date 2021-01-18 16:49
 * @Version 1.0
 */
@RestController
@Api(tags = "房间客人")
@RequestMapping("/api/paas/room/guest")
public class RoomGuestPutController {

    @Autowired
    RoomGuestServiceInterface roomGuestService;

    @ApiOperation("1.退房")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房间客人记录ID", paramType = "path", required = true, dataType = "Integer"),
    })
    @PutMapping("guest/{id}")
    public ApiResponseEntity checkOut(@PathVariable("id") Integer id) throws AppException {
        return roomGuestService.checkOut(id);
    }

    @ApiOperation("2.退房")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "入住业务单号", paramType = "path", required = true, dataType = "Integer"),
    })
    @PutMapping("guest/orderNo/{orderNo}")
    public ApiResponseEntity checkOut(@PathVariable("orderNo") String orderNo) throws AppException {
        return roomGuestService.checkOut(orderNo);
    }
}
