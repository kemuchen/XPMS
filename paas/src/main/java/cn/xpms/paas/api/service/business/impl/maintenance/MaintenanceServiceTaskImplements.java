package cn.xpms.paas.api.service.business.impl.maintenance;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.constant.RoomTaskStatusEnum;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.bean.dto.task.MaintenanceTaskPo;
import cn.xpms.paas.api.bean.dto.task.MaintenanceTaskRoomDeviceRelationVo;
import cn.xpms.paas.api.bean.vo.task.MaintenanceTaskRoomVo;
import cn.xpms.paas.api.bean.vo.task.MaintenanceTaskVo;
import cn.xpms.paas.api.dao.customize.repository.CustomizeTaskMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasMaintenanceTaskMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasMaintenanceTaskRoomDeviceMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasMaintenanceTaskRoomMapper;
import cn.xpms.paas.api.service.business.inter.device.DeviceServiceInterface;
import cn.xpms.paas.api.service.business.inter.maintenance.MaintenanceTaskServiceInterface;
import cn.xpms.paas.api.service.common.inter.api.PaasApiServiceInterface;
import cn.xpms.system.framework.beans.entity.ApiResponseEntity;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MaintenanceServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 14:47
 * @Version 1.0
 */
@Service
public class MaintenanceServiceTaskImplements implements MaintenanceTaskServiceInterface {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(MaintenanceServiceTaskImplements.class);

    /**
     * 涂鸦API接口service
     */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /**
     * 维修任务CURD
     */
    @Autowired
    PaasMaintenanceTaskMapper maintenanceTaskMapper;

    /**
     * 维修任务房间CURD
     */
    @Autowired
    PaasMaintenanceTaskRoomMapper maintenanceTaskRoomMapper;

    /**
     * 维修任务房间设备CURD
     */
    @Autowired
    PaasMaintenanceTaskRoomDeviceMapper maintenanceTaskRoomDeviceMapper;

    /**
     * 任务信息自定义CURD
     */
    @Autowired
    CustomizeTaskMapper customizeTaskMapper;

    /**
     * 设备管理service
     */
    @Autowired
    DeviceServiceInterface deviceServiceInterface;

    /**
     * @Description: 获取维修任务列表
     * @Params: [task]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/4 10:39
     */
    @Override
    public ApiResponseEntity getTasks(PaasMaintenanceTask task) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeTaskMapper.getMaintenanceTasks(task));
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.getTasks】获取维修任务列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询维修任务详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 9:46
     */
    @Override
    public ApiResponseEntity getTask(Integer id) throws AppException {
        try {
            // 查询维修任务信息
            PaasMaintenanceTask task = maintenanceTaskMapper.selectByPrimaryKey(id);
            MaintenanceTaskVo taskVo = new MaintenanceTaskVo();
            taskVo.setTask(task);
            // 查询维修任务房间列表信息
            taskVo.setTask_rooms(this.getMaintenanceTaskRooms(task.getTaskId()));
            return new ApiResponseEntity(SysErrorCode.SUCCESS, taskVo);
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.getTask】查询维修任务详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询维修任务房间列表
     * @Params: [task_id]
     * @return: java.util.List<cn.xpms.paas.api.bean.vo.task.MaintenanceTaskRoomVo>
     * @Author: 柯雷
     * @Date: 2020/12/7 15:44
     */
    private List<MaintenanceTaskRoomVo> getMaintenanceTaskRooms(String task_id) throws AppException {
        try {
            // 查询房间列表
            PaasMaintenanceTaskRoomExample example = new PaasMaintenanceTaskRoomExample();
            example.createCriteria().andTaskIdEqualTo(task_id).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasMaintenanceTaskRoom> rooms = maintenanceTaskRoomMapper.selectByExample(example);

            // 遍历房间列表，返回数据
            List<MaintenanceTaskRoomVo> taskRoomVos = new ArrayList<>();
            MaintenanceTaskRoomVo taskRoomVo;
            for (PaasMaintenanceTaskRoom room : rooms) {
                taskRoomVo = new MaintenanceTaskRoomVo();
                taskRoomVo.setRoom(room);
                // 查询房间维修设备列表
                taskRoomVo.setRoom_device_info(this.getMaintenanceTaskRoomDevices(room.getRoomId(), task_id));
                taskRoomVos.add(taskRoomVo);
            }
            return taskRoomVos;
        } catch (AppException e) {
            logger.error("【MaintenanceServiceTaskImplements.getMaintenanceTaskRooms】查询维修任务房间列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.getMaintenanceTaskRooms】查询维修任务房间列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询维修任务房间设备列表
     * @Params: [room_id, task_id]
     * @return: java.util.List<cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfo>
     * @Author: 柯雷
     * @Date: 2020/12/7 15:51
     */
    private List<PaasDeviceInfo> getMaintenanceTaskRoomDevices(String room_id, String task_id) throws AppException {
        try {
            // 查询房间设备列表
            PaasMaintenanceTaskRoomDeviceExample example = new PaasMaintenanceTaskRoomDeviceExample();
            example.createCriteria().andRoomIdEqualTo(room_id).andTaskIdEqualTo(task_id).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasMaintenanceTaskRoomDevice> roomDevices = maintenanceTaskRoomDeviceMapper.selectByExample(example);

            List<String> device_ids = new ArrayList<>();
            for (PaasMaintenanceTaskRoomDevice device : roomDevices) {
                device_ids.add(device.getDeviceId());
            }
            return deviceServiceInterface.getDevices(device_ids);
        } catch (AppException e) {
            logger.error("【MaintenanceServiceTaskImplements.getMaintenanceTaskRoomDevices】查询维修任务房间设备列表异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.getMaintenanceTaskRoomDevices】查询维修任务房间设备列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 创建维修任务
     * @Params: [maintenanceTaskPo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:34
     */
    @Override
    public ApiResponseEntity createTask(MaintenanceTaskPo maintenanceTaskPo) throws AppException {
        try {
            // 调用涂鸦接口
            Map<String, Object> params = BeanUtil.beanToMap(maintenanceTaskPo.getPaasMaintenanceTask(), true);
            params.put("room_device_relations", maintenanceTaskPo.getRoomDeviceRelations());
            PaasApiResponseEntity<String> responseEntity = paasApiServiceInterface.call(PaasApiEnum.ADD_MAINTENANCE_TASK, params);

            // 新增维修任务
            PaasMaintenanceTask task = maintenanceTaskPo.getPaasMaintenanceTask();
            task.setTaskId(responseEntity.getResult());
            task.setStatus(RoomTaskStatusEnum.PENDING_CONSTRUCTION.toString());
            maintenanceTaskMapper.insertSelective(task);

            // 新增维修任务房间
            this.createMaintenanceTaskRoom(maintenanceTaskPo.getRoomDeviceRelations(), task.getTaskId());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【MaintenanceServiceTaskImplements.createTask】创建维修任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.createTask】创建维修任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增维修任务房间
     * @Params: [roomDeviceRelationVos, task_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/7 11:44
     */
    private void createMaintenanceTaskRoom(List<MaintenanceTaskRoomDeviceRelationVo> roomDeviceRelationVos,
                                           String task_id) throws AppException {
        try {
            PaasMaintenanceTaskRoom room;
            for (MaintenanceTaskRoomDeviceRelationVo roomDeviceRelationVo : roomDeviceRelationVos) {
                // 新增维修任务房间
                room = new PaasMaintenanceTaskRoom();
                room.setRoomId(roomDeviceRelationVo.getRoom_id());
                room.setTaskId(task_id);
                maintenanceTaskRoomMapper.insertSelective(room);

                // 新增房间设备信息
                this.createMaintenanceTaskRoomDevice(roomDeviceRelationVo.getDevice_ids(), room.getRoomId(), task_id);
            }
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.createMaintenanceTaskRoom】新增维修任务房间异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增维修任务房间设备
     * @Params: [device_ids, room_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/7 11:46
     */
    private void createMaintenanceTaskRoomDevice(List<String> device_ids, String room_id, String task_id) throws AppException {
        try {
            PaasMaintenanceTaskRoomDevice roomDevice;
            for (String device_id : device_ids) {
                // 新增维修任务房间设备
                roomDevice = new PaasMaintenanceTaskRoomDevice();
                roomDevice.setRoomId(room_id);
                roomDevice.setDeviceId(device_id);
                roomDevice.setTaskId(task_id);
                maintenanceTaskRoomDeviceMapper.insertSelective(roomDevice);
            }
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.createMaintenanceTaskRoomDevice】新增维修任务房间设备异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 重开放维修任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:34
     */
    @Override
    public ApiResponseEntity reopenTask(Integer id) throws AppException {
        try {
            // 调用涂鸦接口
            PaasMaintenanceTask task = maintenanceTaskMapper.selectByPrimaryKey(id);
            paasApiServiceInterface.call(PaasApiEnum.MAINTENANCE_TASK_REOPEN,
                    BeanUtil.beanToMap(task, true));
            task.setStatus(RoomTaskStatusEnum.PENDING_CONSTRUCTION.toString());

            // 更新维修任务状态
            maintenanceTaskMapper.updateByPrimaryKeySelective(task);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【MaintenanceServiceTaskImplements.reopenTask】重开放维修任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.reopenTask】重开放维修任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 验收维修任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:34
     */
    @Override
    public ApiResponseEntity acceptanceTask(Integer id) throws AppException {
        try {
            // 调用涂鸦接口
            PaasMaintenanceTask task = maintenanceTaskMapper.selectByPrimaryKey(id);
            paasApiServiceInterface.call(PaasApiEnum.MAINTENANCE_TASK_ACCEPTANCE,
                    BeanUtil.beanToMap(task, true));
            task.setStatus(RoomTaskStatusEnum.ACCEPTANCE_COMPLETE.toString());

            // 更新维修任务状态
            maintenanceTaskMapper.updateByPrimaryKeySelective(task);
            // 同步房间设备信息
            this.synchroRoomDevice(task.getTaskId());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【MaintenanceServiceTaskImplements.acceptanceTask】验收维修任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.acceptanceTask】验收维修任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步房间设备信息
     * @Params: [task_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 11:16
     */
    private void synchroRoomDevice(String task_id) throws AppException {
        try {
            // 查询房间列表
            PaasMaintenanceTaskRoomExample example = new PaasMaintenanceTaskRoomExample();
            example.createCriteria().andTaskIdEqualTo(task_id).andValidEqualTo(SystemConstants.INT_YES);
            List<PaasMaintenanceTaskRoom> rooms = maintenanceTaskRoomMapper.selectByExample(example);

            for (PaasMaintenanceTaskRoom room : rooms) {
                deviceServiceInterface.synchroRoomDevice(room.getRoomId());
            }
        } catch (AppException e) {
            logger.error("【MaintenanceServiceTaskImplements.synchroRoomDevice】同步房间设备信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.synchroRoomDevice】同步房间设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除维修任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 15:35
     */
    @Override
    public ApiResponseEntity deleteTask(Integer id) throws AppException {
        try {
            // 调用涂鸦接口
            PaasMaintenanceTask task = maintenanceTaskMapper.selectByPrimaryKey(id);
            Map<String, Object> params = new HashMap<>();
            params.put("project_id", task.getProjectId());
            params.put("task_id", task.getTaskId());
            paasApiServiceInterface.call(PaasApiEnum.DELETE_MAINTENANCE_TASK, params);

            task.setValid(SystemConstants.INT_NO);
            // 更新施工任务
            maintenanceTaskMapper.updateByPrimaryKeySelective(task);
            // 删除维修任务房间
            this.deleteTaskRoom(task.getTaskId());
            // 删除维修任务房间设备
            this.deleteTaskRoomDevice(task.getTaskId());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【MaintenanceServiceTaskImplements.deleteTask】删除施工任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.deleteTask】删除施工任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除维修任务房间
     * @Params: [task_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/7 12:01
     */
    private void deleteTaskRoom(String task_id) throws AppException {
        try {
            // 删除施工任务房间
            PaasMaintenanceTaskRoomExample example = new PaasMaintenanceTaskRoomExample();
            example.createCriteria().andTaskIdEqualTo(task_id);
            PaasMaintenanceTaskRoom room = new PaasMaintenanceTaskRoom();
            room.setValid(SystemConstants.INT_NO);
            maintenanceTaskRoomMapper.updateByExampleSelective(room, example);
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.deleteTaskRoom】删除维修任务房间异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除维修任务房间设备
     * @Params: [task_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/7 12:02
     */
    private void deleteTaskRoomDevice(String task_id) throws AppException {
        try {
            // 删除维修任务房间设备
            PaasMaintenanceTaskRoomDeviceExample example = new PaasMaintenanceTaskRoomDeviceExample();
            example.createCriteria().andTaskIdEqualTo(task_id);
            PaasMaintenanceTaskRoomDevice roomDevice = new PaasMaintenanceTaskRoomDevice();
            roomDevice.setValid(SystemConstants.INT_NO);
            maintenanceTaskRoomDeviceMapper.updateByExampleSelective(roomDevice, example);
        } catch (Exception e) {
            logger.error("【MaintenanceServiceTaskImplements.deleteTaskRoomDevice】删除维修任务房间设备异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}