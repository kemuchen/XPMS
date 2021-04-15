package cn.xpms.paas.api.service.business.inter.device;

import cn.xpms.paas.api.bean.constant.AutomationDeviceTypeEnum;
import cn.xpms.paas.api.bean.dto.device.PaasDeviceQuery;
import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfo;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DeviceServiceInterface
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/7 15:54
 * @Version 1.0
 */
public interface DeviceServiceInterface {

    /**
     * @Description: 获取设备列表
     * @Params: [device_ids]
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfo>
     * @Author: 柯雷
     * @Date: 2020/12/7 15:55
     */
    List<PaasDeviceInfo> getDevices(List<String> device_ids) throws AppException;

    /**
     * @Description: 同步房间设备信息
     * @Params: [room_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 8:55
     */
    void synchroRoomDevice(String room_id) throws AppException;

    /**
     * @Description: 同步房间支持场景的设备列表
     * @Params: [room_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/11 9:26
     */
    void synchroRoomSceneDevice(String room_id) throws AppException;

    /**
     * @Description: 同步房间支持的自动化设备列表
     * @Params: [room_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/11 9:26
     */
    void synchroRoomAutmationDevice(String room_id, AutomationDeviceTypeEnum type) throws AppException;

    /**
     * @Description: 同步设备详细信息
     * @Params: [device_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 9:31
     */
    void synchroDeviceInfo(PaasDeviceInfo paasDeviceInfo) throws AppException;

    /**
     * @Description: 同步房间设备规格属性
     * @Params: [device_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 8:57
     */
    void synchroRoomDeviceSpecifications(String device_id, String category) throws AppException;

    void synchronizeAutomationDevice(String roomId) throws AppException;

    /**
     * @Description: 同步设备支持的规格列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2021-01-25 14:23
     */
    void synchronizeAutomationProperty(String device_id) throws AppException;

    /**
     * @Description: 获取设备品类功能集
     * @Params: [device_id]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 14:53
     */
    ApiResponseEntity getDeviceFunction(String device_id) throws AppException;

    ApiResponseEntity getAutomationDeviceFunction(String device_id) throws AppException;

    /**
     * @Description: 获取设备状态集
     * @Params: [device_id]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:03
     */
    ApiResponseEntity getDeviceStatus(String device_id) throws AppException;

    ApiResponseEntity getAutomationDeviceStatus(String device_id) throws AppException;

    ApiResponseEntity getDeviceInfos(PaasDeviceQuery paasDeviceQuery) throws AppException;
}