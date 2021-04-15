package cn.xpms.paas.api.web.room;

import cn.xpms.paas.api.service.business.inter.room.RoomGuestServiceInterface;
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
 * @ClassName RoomGuestGetController
 * @Desc
 * @Author ycj
 * @Date 2021-01-18 16:53
 * @Version 1.0
 */
@RestController
@Api(tags = "房间客人")
@RequestMapping("/api/paas/room/guest")
public class RoomGuestGetController {
    @Autowired
    RoomGuestServiceInterface roomGuestService;

    @ApiOperation("1.订单客人详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房间客人记录ID", paramType = "path", required = true, dataType = "Integer"),
    })
    @GetMapping("guest/{id}")
    public ApiResponseEntity getCheckInGuestDetailById(@PathVariable("id") Integer id) throws AppException {
        return roomGuestService.getCheckInGuestDetailById(id);
    }

    @ApiOperation("1.订单客人详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNo", value = "提交记录外部单号", paramType = "path", required = true, dataType = "String"),
    })
    @GetMapping("guest/orderNo/{orderNo}")
    public ApiResponseEntity getCheckInGuestDetailByOrderNo(@PathVariable("orderNo") String orderNo) throws AppException {
        return roomGuestService.getCheckInGuestDetailByOrderNo(orderNo);
    }
}
