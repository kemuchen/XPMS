package cn.xpms.paas.api.service.business.inter.room;

import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

/**
 * @ClassName RoomServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/1 8:33
 * @Version 1.0
 */
public interface RoomServiceInterface {

    /**
     * @Description: 新建房间
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 14:57
     */
    ApiResponseEntity createRoom(PaasRoomInfo paasRoomInfo) throws AppException;

    /**
     * @Description: 查询房间列表
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 14:58
     */
    ApiResponseEntity getRooms(PaasRoomInfo paasRoomInfo) throws AppException;

    /**
     * @Description: 查询房间详情信息
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 14:59
     */
    ApiResponseEntity getRoomDetail(Integer id) throws AppException;

    /**
     * @Description: 修改房间信息
     * @Params: [paasRoomInfo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 14:59
     */
    ApiResponseEntity modifyRoom(PaasRoomInfo paasRoomInfo) throws AppException;

    /**
     * @Description: 删除房间
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:00
     */
    ApiResponseEntity deleteRoom(Integer id) throws AppException;

    /**
     * @Description: 获取房间施工状态
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/1 15:01
     */
    ApiResponseEntity getRoomStatus(Integer id) throws AppException;

    /**
     * @Description: 查询房态
     * @Params: PaasRoomInfo
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: ycj
     * @Date: 2020-12-12 10:34
     */
    ApiResponseEntity getRoomsStatus(PaasRoomInfo roomInfo) throws AppException;
}