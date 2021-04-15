package cn.xpms.paas.api.service.business.inter.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomGuest;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName RoomGuestInterface
 * @Desc
 * @Author ycj
 * @Date 2021-01-18 16:41
 * @Version 1.0
 */
public interface RoomGuestServiceInterface {

    ApiResponseEntity checkIn(PaasRoomGuest roomGuest) throws AppException;

    ApiResponseEntity getCheckInGuestDetailById(Integer id) throws AppException;

    ApiResponseEntity getCheckInGuestDetailByOrderNo(String orderNo) throws AppException;

    ApiResponseEntity checkOut(Integer id) throws AppException;

    ApiResponseEntity checkOut(String orderNo) throws AppException;
}
