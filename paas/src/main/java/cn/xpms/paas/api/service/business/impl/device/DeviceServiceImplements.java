package cn.xpms.paas.api.service.business.impl.device;

import cn.xpms.paas.api.bean.constant.AutomationDeviceTypeEnum;
import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.bean.dto.device.PaasDeviceQuery;
import cn.xpms.paas.api.bean.vo.device.DeviceSpecificationsVo;
import cn.xpms.paas.api.bean.vo.device.PaasDeviceInfoPageResultVo;
import cn.xpms.paas.api.dao.customize.repository.CustomizeDeviceMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasDeviceInfoFunctionMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasDeviceInfoMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasDeviceInfoStatusMapper;
import cn.xpms.paas.api.service.business.inter.device.DeviceServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DeviceServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/7 15:55
 * @Version 1.0
 */
@Service
public class DeviceServiceImplements implements DeviceServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(DeviceServiceImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 设备信息CURD
     */
    @Autowired
    PaasDeviceInfoMapper paasDeviceInfoMapper;

    /**
     * 设备品类功能集CURD
     */
    @Autowired
    PaasDeviceInfoFunctionMapper deviceInfoFunctionMapper;

    /**
     * 设备状态集CURD
     */
    @Autowired
    PaasDeviceInfoStatusMapper deviceInfoStatusMapper;

    @Autowired
    CustomizeDeviceMapper customizeDeviceMapper;

    /**
     * @Description: 获取设备列表
     * @Params: [device_ids]
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfo>
     * @Author: 柯雷
     * @Date: 2020/12/8 9:19
     */
    @Override
    public List<PaasDeviceInfo> getDevices(List<String> device_ids) throws AppException {
        try {
            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andDeviceIdIn(device_ids);
            return paasDeviceInfoMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.getDevices】获取设备列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步房间设备信息
     * @Params: [room_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 9:23
     */
    @Override
    public void synchroRoomDevice(String room_id) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("room_id", room_id);

            // 调用涂鸦接口
            PaasApiResponseEntity<PaasDeviceInfoPageResultVo> responseEntity =
                    paasApiServiceInterface.callPage(PaasApiEnum.GET_ROOM_DEVICES, params, PaasDeviceInfoPageResultVo.class);

            List<String> device_ids = new ArrayList<>();
            for (PaasDeviceInfo deviceInfo : responseEntity.getResult().getDevices()) {
                device_ids.add(deviceInfo.getDeviceId());
            }

            if (device_ids.size() < 1) {
                return;
            }

            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andDeviceIdNotIn(device_ids).andRoomIdEqualTo(room_id);
            PaasDeviceInfo deleteRecords = new PaasDeviceInfo();
            deleteRecords.setValid(SystemConstants.INT_NO);
            paasDeviceInfoMapper.updateByExampleSelective(deleteRecords, example);

            // 遍历设备同步设备详情
            for (PaasDeviceInfo deviceInfo : responseEntity.getResult().getDevices()) {
                this.synchroDeviceInfo(deviceInfo);
            }
            // 同步房间支持场景的设备列表
            this.synchroRoomSceneDevice(room_id);
            // 同步房间支持自动化的条件设备列表
            this.synchroRoomAutmationDevice(room_id, AutomationDeviceTypeEnum.condition);
            // 同步房间支持自动化的动作设备设备列表
            this.synchroRoomAutmationDevice(room_id, AutomationDeviceTypeEnum.action);
            // 同步房间支持自动化设备规格属性
//            this.synchronizeAutomationDevice(room_id);
            for (String device_id : device_ids) {
                this.synchronizeAutomationProperty(device_id);
            }
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.synchroRoomDevice】同步房间设备信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.synchroRoomDevice】同步房间设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步房间支持场景的设备列表
     * @Params: [room_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/11 9:27
     */
    @Override
    public void synchroRoomSceneDevice(String room_id) throws AppException {
        try {
            // 调用涂鸦接口
            Map<String, Object> params = new HashMap<>();
            params.put("room_id", room_id);
            PaasApiResponseEntity<PaasDeviceInfoPageResultVo> apiResponseEntity =
                    paasApiServiceInterface.callPage(PaasApiEnum.GET_ROOM_SCENE_DEVICES, params, PaasDeviceInfoPageResultVo.class);

            // 更新设备支持场景标识
            PaasDeviceInfo deviceInfo = new PaasDeviceInfo();
            deviceInfo.setScene(SystemConstants.INT_YES);
            this.updateDeviceInfo(deviceInfo, apiResponseEntity.getResult().getDevices());
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.synchroRoomSceneDevice】同步房间支持场景的设备列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.synchroRoomSceneDevice】同步房间支持场景的设备列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步房间支持自动化的设备列表
     * @Params: [room_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/11 9:27
     */
    @Override
    public void synchroRoomAutmationDevice(String room_id, AutomationDeviceTypeEnum type) throws AppException {
        try {
            // 调用涂鸦接口
            Map<String, Object> params = new HashMap<>();
            params.put("room_id", room_id);
            params.put("type", type.toString());
            PaasApiResponseEntity<PaasDeviceInfoPageResultVo> apiResponseEntity =
                    paasApiServiceInterface.callPage(PaasApiEnum.GET_ROOM_AUTOMATION_DEVICES, params, PaasDeviceInfoPageResultVo.class);

            // 更新支持自动化设备的标志
            PaasDeviceInfo deviceInfo = new PaasDeviceInfo();
            if (type.toString().equals(AutomationDeviceTypeEnum.action.toString())) {
                deviceInfo.setAction(SystemConstants.INT_YES);
            } else {
                deviceInfo.setCondition(SystemConstants.INT_YES);
            }
            this.updateDeviceInfo(deviceInfo, apiResponseEntity.getResult().getDevices());
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.synchroRoomAutmationDevice】同步房间支持自动化的设备列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.synchroRoomAutmationDevice】同步房间支持自动化的设备列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新设备信息
     * @Params: [device, deviceInfos]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/11 11:16
     */
    private void updateDeviceInfo(PaasDeviceInfo device, List<PaasDeviceInfo> deviceInfos) throws AppException {
        try {
            // 遍历设备，更新
            List<String> device_ids = new ArrayList<>();
            for (PaasDeviceInfo deviceInfo : deviceInfos) {
                device_ids.add(deviceInfo.getDeviceId());
            }
            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andDeviceIdIn(device_ids);
            paasDeviceInfoMapper.updateByExampleSelective(device, example);
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.updateDeviceInfo】更新设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步设备详细信息
     * @Params: [device_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 9:38
     */
    @Override
    public void synchroDeviceInfo(PaasDeviceInfo paasDeviceInfo) throws AppException {
        try {
            // 查询设备是否存在，存在则更新，不存在则新增
            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andDeviceIdEqualTo(paasDeviceInfo.getDeviceId()).andValidEqualTo(SystemConstants.INT_YES);
            if (paasDeviceInfoMapper.countByExample(example) > 0) {
                paasDeviceInfoMapper.updateByExampleSelective(paasDeviceInfo, example);
            } else {
                paasDeviceInfoMapper.insertSelective(paasDeviceInfo);
            }
            // 同步设备规格属性信息
            this.synchroRoomDeviceSpecifications(paasDeviceInfo.getDeviceId(), paasDeviceInfo.getCategory());
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.synchroDeviceInfo】同步设备详细信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.synchroDeviceInfo】同步设备详细信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步设备规格属性信息
     * @Params: [device_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 9:41
     */
    @Override
    public void synchroRoomDeviceSpecifications(String device_id, String category) throws AppException {
        try {
            // 调用涂鸦接口
            Map<String, Object> params = new HashMap<>();
            params.put("device_id", device_id);
            PaasApiResponseEntity<DeviceSpecificationsVo> responseEntity = paasApiServiceInterface.call(
                    PaasApiEnum.GET_DEVICE_SPECIFICATIONS, params, DeviceSpecificationsVo.class);
            DeviceSpecificationsVo specificationsVo = responseEntity.getResult();
            // 保存设备品类功能集
            this.saveRoomDeviceInfoFunctions(specificationsVo.getFunctions(), device_id, category);
            // 保存设备状态集
            this.saveRoomDeviceInfoStatus(specificationsVo.getStatus(), device_id);
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.synchroRoomDeviceSpecifications】同步设备规格属性信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.synchroRoomDeviceSpecifications】同步设备规格属性信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public void synchronizeAutomationDevice(String roomId) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("room_id", roomId);
            // 调用涂鸦接口
            PaasApiResponseEntity<Map<String, Object>> responseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.GET_ROOM_AUTOMATION_DEVICE_SPECIFICATIONS, params);

            if (responseEntity.getSuccess()) {
                Map<String, Object> result = responseEntity.getResult();
                List<Map<String, Object>> devices = (List<Map<String, Object>>) result.get("devices");
                for (Map<String, Object> device : devices) {
                    String device_id = device.get("device_id").toString();

                    List<Map<String, Object>> functions = (List<Map<String, Object>>) device.get("functions");
                    if (null != functions && functions.size() > 0) {
                        List<String> functionCodes = new ArrayList<>();
                        for (Map<String, Object> function : functions) {
                            functionCodes.add(function.get("code").toString());
                        }

                        if (functionCodes.size() > 0) {
                            PaasDeviceInfoFunctionExample cannotFunctionExample = new PaasDeviceInfoFunctionExample();
                            cannotFunctionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                    .andCodeNotIn(functionCodes);
                            PaasDeviceInfoFunction cannotFunctions = new PaasDeviceInfoFunction();
                            cannotFunctions.setAutomation(SystemConstants.INT_NO);
                            deviceInfoFunctionMapper.updateByExampleSelective(cannotFunctions, cannotFunctionExample);

                            PaasDeviceInfoFunctionExample canFunctionExample = new PaasDeviceInfoFunctionExample();
                            canFunctionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                    .andCodeIn(functionCodes);
                            PaasDeviceInfoFunction canFunctions = new PaasDeviceInfoFunction();
                            canFunctions.setAutomation(SystemConstants.INT_YES);
                            deviceInfoFunctionMapper.updateByExampleSelective(canFunctions, canFunctionExample);
                        } else {
                            PaasDeviceInfoFunctionExample cannotFunctionExample = new PaasDeviceInfoFunctionExample();
                            cannotFunctionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id);
                            PaasDeviceInfoFunction cannotFunctions = new PaasDeviceInfoFunction();
                            cannotFunctions.setAutomation(SystemConstants.INT_NO);
                            deviceInfoFunctionMapper.updateByExampleSelective(cannotFunctions, cannotFunctionExample);
                        }
                    }

                    List<Map<String, Object>> statusList = (List<Map<String, Object>>) device.get("status");
                    if (null != statusList && statusList.size() > 0) {
                        List<String> statusCodes = new ArrayList<>();
                        for (Map<String, Object> status : statusList) {
                            statusCodes.add(status.get("code").toString());
                        }

                        if (statusCodes.size() > 0) {
                            PaasDeviceInfoStatusExample cannotStatusExample = new PaasDeviceInfoStatusExample();
                            cannotStatusExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                    .andCodeNotIn(statusCodes);
                            PaasDeviceInfoStatus cannotStatus = new PaasDeviceInfoStatus();
                            cannotStatus.setAutomation(SystemConstants.INT_NO);
                            deviceInfoStatusMapper.updateByExampleSelective(cannotStatus, cannotStatusExample);

                            PaasDeviceInfoStatusExample canStatusExample = new PaasDeviceInfoStatusExample();
                            canStatusExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                    .andCodeIn(statusCodes);
                            PaasDeviceInfoStatus canStatus = new PaasDeviceInfoStatus();
                            canStatus.setAutomation(SystemConstants.INT_YES);
                            deviceInfoStatusMapper.updateByExampleSelective(canStatus, canStatusExample);
                        } else {
                            PaasDeviceInfoStatusExample cannotStatusExample = new PaasDeviceInfoStatusExample();
                            cannotStatusExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id);
                            PaasDeviceInfoStatus cannotStatus = new PaasDeviceInfoStatus();
                            cannotStatus.setAutomation(SystemConstants.INT_NO);
                            deviceInfoStatusMapper.updateByExampleSelective(cannotStatus, cannotStatusExample);
                        }

                    }
                }
            }
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.synchronizeAutomationDevice】同步房间支持联动的设备规格列表异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.synchronizeAutomationDevice】同步房间支持联动的设备规格列表异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步设备支持的规格列表
     * @Params:
     * @return:
     * @Author: ycj
     * @Date: 2021-01-25 14:24
     */
    @Override
    public void synchronizeAutomationProperty(String device_id) throws AppException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("device_id", device_id);
            // 调用涂鸦接口
            PaasApiResponseEntity<Map<String, Object>> responseEntity =
                    paasApiServiceInterface.call(PaasApiEnum.GET_DEVICE_AUTOMATION_SPECIFICATIONS, params);

            if (responseEntity.getSuccess()) {
                Map<String, Object> result = responseEntity.getResult();

                List<Map<String, Object>> functions = (List<Map<String, Object>>) result.get("functions");
                if (null != functions && functions.size() > 0) {
                    List<String> functionCodes = new ArrayList<>();
                    for (Map<String, Object> function : functions) {
                        functionCodes.add(function.get("code").toString());
                    }

                    if(functionCodes.size()>0) {
                        PaasDeviceInfoFunctionExample cannotFunctionExample = new PaasDeviceInfoFunctionExample();
                        cannotFunctionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                .andCodeNotIn(functionCodes);
                        PaasDeviceInfoFunction cannotFunctions = new PaasDeviceInfoFunction();
                        cannotFunctions.setAutomation(SystemConstants.INT_NO);
                        deviceInfoFunctionMapper.updateByExampleSelective(cannotFunctions, cannotFunctionExample);

                        PaasDeviceInfoFunctionExample canFunctionExample = new PaasDeviceInfoFunctionExample();
                        canFunctionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                .andCodeIn(functionCodes);
                        PaasDeviceInfoFunction canFunctions = new PaasDeviceInfoFunction();
                        canFunctions.setAutomation(SystemConstants.INT_YES);
                        deviceInfoFunctionMapper.updateByExampleSelective(canFunctions, canFunctionExample);
                    }else {
                        PaasDeviceInfoFunctionExample cannotFunctionExample = new PaasDeviceInfoFunctionExample();
                        cannotFunctionExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id);
                        PaasDeviceInfoFunction cannotFunctions = new PaasDeviceInfoFunction();
                        cannotFunctions.setAutomation(SystemConstants.INT_NO);
                        deviceInfoFunctionMapper.updateByExampleSelective(cannotFunctions, cannotFunctionExample);
                    }
                }

                List<Map<String, Object>> statusList = (List<Map<String, Object>>) result.get("status");
                if (null != statusList && statusList.size() > 0) {
                    List<String> statusCodes = new ArrayList<>();
                    for (Map<String, Object> status : statusList) {
                        statusCodes.add(status.get("code").toString());
                    }

                    if (statusCodes.size() > 0) {
                        PaasDeviceInfoStatusExample cannotStatusExample = new PaasDeviceInfoStatusExample();
                        cannotStatusExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                .andCodeNotIn(statusCodes);
                        PaasDeviceInfoStatus cannotStatus = new PaasDeviceInfoStatus();
                        cannotStatus.setAutomation(SystemConstants.INT_NO);
                        deviceInfoStatusMapper.updateByExampleSelective(cannotStatus, cannotStatusExample);

                        PaasDeviceInfoStatusExample canStatusExample = new PaasDeviceInfoStatusExample();
                        canStatusExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id)
                                .andCodeIn(statusCodes);
                        PaasDeviceInfoStatus canStatus = new PaasDeviceInfoStatus();
                        canStatus.setAutomation(SystemConstants.INT_YES);
                        deviceInfoStatusMapper.updateByExampleSelective(canStatus, canStatusExample);
                    } else {
                        PaasDeviceInfoStatusExample cannotStatusExample = new PaasDeviceInfoStatusExample();
                        cannotStatusExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES).andDeviceIdEqualTo(device_id);
                        PaasDeviceInfoStatus cannotStatus = new PaasDeviceInfoStatus();
                        cannotStatus.setAutomation(SystemConstants.INT_NO);
                        deviceInfoStatusMapper.updateByExampleSelective(cannotStatus, cannotStatusExample);
                    }

                }
            }
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.synchronizeAutomationProperty】同步设备支持的规格列表异常:" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.synchronizeAutomationProperty】同步设备支持的规格列表异常:" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存品类功能集
     * @Params: [infoFunctions, device_id, category]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 10:55
     */
    private void saveRoomDeviceInfoFunctions(List<PaasDeviceInfoFunction> infoFunctions,
                                             String device_id, String category) throws AppException {
        try {
            // 先物理删除品类功能集数据
            this.deleteRoomDeviceInfoFunctions(device_id);

            // 新增设备品类功能集
            for (PaasDeviceInfoFunction function : infoFunctions) {
                function.setDeviceId(device_id);
                function.setCategory(category);
                deviceInfoFunctionMapper.insertSelective(function);
            }
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.saveRoomDeviceInfoFunctions】保存品类功能集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除设备品类功能集
     * @Params: [device_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 10:58
     */
    private void deleteRoomDeviceInfoFunctions(String device_id) throws AppException {
        try {
            PaasDeviceInfoFunctionExample example = new PaasDeviceInfoFunctionExample();
            example.createCriteria().andDeviceIdEqualTo(device_id);

            PaasDeviceInfoFunction function = new PaasDeviceInfoFunction();
            function.setValid(SystemConstants.INT_NO);
            deviceInfoFunctionMapper.updateByExampleSelective(function, example);
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.deleteRoomDeviceInfoFunctions】删除设备品类功能集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存设备状态集
     * @Params: [infoStatus, device_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 10:55
     */
    private void saveRoomDeviceInfoStatus(List<PaasDeviceInfoStatus> infoStatus, String device_id) throws AppException {
        try {
            // 先物理删除设备状态集数据
            this.deleteRoomDeviceInfoStatus(device_id);

            // 新增设备状态集
            for (PaasDeviceInfoStatus status : infoStatus) {
                status.setDeviceId(device_id);
                deviceInfoStatusMapper.insertSelective(status);
            }
        } catch (AppException e) {
            logger.error("【DeviceServiceImplements.saveRoomDeviceInfoStatus】保存设备状态集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除设备状态集
     * @Params: [device_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 11:06
     */
    private void deleteRoomDeviceInfoStatus(String device_id) throws AppException {
        try {
            PaasDeviceInfoStatusExample example = new PaasDeviceInfoStatusExample();
            example.createCriteria().andDeviceIdEqualTo(device_id);

            PaasDeviceInfoStatus status = new PaasDeviceInfoStatus();
            status.setValid(SystemConstants.INT_NO);
            deviceInfoStatusMapper.updateByExampleSelective(status, example);
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.deleteRoomDeviceInfoStatus】删除设备状态集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取设备品类功能集
     * @Params: [device_id]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 14:54
     */
    @Override
    public ApiResponseEntity getDeviceFunction(String device_id) throws AppException {
        try {
            PaasDeviceInfoFunctionExample example = new PaasDeviceInfoFunctionExample();
            example.createCriteria().andDeviceIdEqualTo(device_id).andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, deviceInfoFunctionMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.getDeviceFunction】获取设备品类功能集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getAutomationDeviceFunction(String device_id) throws AppException {
        try {
            PaasDeviceInfoFunctionExample example = new PaasDeviceInfoFunctionExample();
            example.createCriteria().andDeviceIdEqualTo(device_id).andValidEqualTo(SystemConstants.INT_YES)
                    .andAutomationEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, deviceInfoFunctionMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.getAutomationDeviceFunction】获取支持自动化的设备品类功能集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取设备状态集
     * @Params: [device_id]
     * @return:
     * @Author: ycj
     * @Date: 2020-12-19 16:04
     */
    @Override
    public ApiResponseEntity getDeviceStatus(String device_id) throws AppException {
        try {
            PaasDeviceInfoStatusExample example = new PaasDeviceInfoStatusExample();
            example.createCriteria().andDeviceIdEqualTo(device_id).andValidEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, deviceInfoStatusMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.getDeviceFunction】获取设备状态集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getAutomationDeviceStatus(String device_id) throws AppException {
        try {
            PaasDeviceInfoStatusExample example = new PaasDeviceInfoStatusExample();
            example.createCriteria().andDeviceIdEqualTo(device_id).andValidEqualTo(SystemConstants.INT_YES)
                    .andAutomationEqualTo(SystemConstants.INT_YES);
            return new ApiResponseEntity(SysErrorCode.SUCCESS, deviceInfoStatusMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.getAutomationDeviceStatus】获取支持自动化的设备状态集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    @Override
    public ApiResponseEntity getDeviceInfos(PaasDeviceQuery paasDeviceQuery) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeDeviceMapper.getDeviceInfos(paasDeviceQuery));
        } catch (Exception e) {
            logger.error("【DeviceServiceImplements.getAutomationDeviceStatus】获取支持自动化的设备状态集异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}