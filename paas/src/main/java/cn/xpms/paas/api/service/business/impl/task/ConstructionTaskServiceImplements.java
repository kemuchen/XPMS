package cn.xpms.paas.api.service.business.impl.task;

import cn.xpms.paas.api.bean.constant.PaasApiEnum;
import cn.xpms.paas.api.bean.constant.RoomTaskStatusEnum;
import cn.xpms.paas.api.bean.dto.task.ConstructionTemplateTaskPo;
import cn.xpms.paas.api.bean.dto.common.PaasApiResponseEntity;
import cn.xpms.paas.api.bean.vo.task.ConstructionTemplateTaskVo;
import cn.xpms.paas.api.dao.customize.repository.CustomizeTaskMapper;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.PaasConstructionTaskMapper;
import cn.xpms.paas.api.dao.generator.repository.PaasConstructionTaskRoomMapper;
import cn.xpms.paas.api.service.business.inter.device.DeviceServiceInterface;
import cn.xpms.paas.api.service.business.inter.task.ConstructionTaskServiceInterface;
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
import java.util.List;
import java.util.Map;

/**
 * @ClassName ConstructionTaskServiceImplements
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/4 9:00
 * @Version 1.0
 */
@Service
public class ConstructionTaskServiceImplements implements ConstructionTaskServiceInterface {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(ConstructionTaskServiceImplements.class);

    /** 涂鸦API接口service */
    @Autowired
    PaasApiServiceInterface paasApiServiceInterface;

    /** 设备管理service */
    @Autowired
    DeviceServiceInterface deviceServiceInterface;

    /** 施工任务CURD */
    @Autowired
    PaasConstructionTaskMapper taskMapper;

    /** 施工房间列表CURD */
    @Autowired
    PaasConstructionTaskRoomMapper taskRoomMapper;

    /** 自定义施工任务信息CURD */
    @Autowired
    CustomizeTaskMapper customizeTaskMapper;

    /**
     * @Description: 获取施工任务列表
     * @Params: [task]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseResultEntity
     * @Author: 柯雷
     * @Date: 2020/12/4 10:39
     */
    @Override
    public ApiResponseEntity getTasks(PaasConstructionTask task) throws AppException {
        try {
            return new ApiResponseEntity(SysErrorCode.SUCCESS, customizeTaskMapper.getConstructionTasks(task));
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.getTasks】查询施工任务列表异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 查询施工任务详情
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 9:46
     */
    @Override
    public ApiResponseEntity getTask(Integer id) throws AppException {
        try {
            // 查询施工任务信息
            PaasConstructionTask task = taskMapper.selectByPrimaryKey(id);
            ConstructionTemplateTaskVo taskVo = new ConstructionTemplateTaskVo();
            taskVo.setTask(task);
            // 查询施工任务房间列表信息
            taskVo.setRooms(customizeTaskMapper.getTaskRoomInfo(task.getTaskId()));
            return new ApiResponseEntity(SysErrorCode.SUCCESS, taskVo);
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.getTask】查询施工任务详情异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 创建施工模板任务
     * @Params: [taskVo]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 10:01
     */
    @Override
    public ApiResponseEntity createTemplateTask(ConstructionTemplateTaskPo taskPo) throws AppException {
        try {
            // 调用涂鸦接口
            Map<String, Object> params = BeanUtil.beanToMap(taskPo.getTask(), true);
            params.put("room_ids", taskPo.getRoom_ids());
            PaasApiResponseEntity<String> responseEntity = paasApiServiceInterface.call(PaasApiEnum.ADD_TEMPLATE_TASK, params);

            // 新增模板施工任务
            PaasConstructionTask task = taskPo.getTask();
            task.setTaskId(responseEntity.getResult());
            task.setStatus(RoomTaskStatusEnum.PENDING_CONSTRUCTION.toString());
            task.setTemplateConstraint(SystemConstants.STR_FALSE);
            taskMapper.insertSelective(task);

            // 新增模板施工任务房间
            this.createTaskRoom(taskPo.getRoom_ids(), task.getTaskId());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ConstructionTaskServiceImplements.createTemplateTask】创建模板施工任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.createTemplateTask】创建模板施工任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 新增施工模板任务房间信息
     * @Params: [room_ids, task_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/5 10:51
     */
    private void createTaskRoom(List<String> room_ids, String task_id) throws AppException {
        try {
            PaasConstructionTaskRoom room;
            for (String room_id : room_ids) {
                room = new PaasConstructionTaskRoom();
                room.setRoomId(room_id);
                room.setTaskId(task_id);
                taskRoomMapper.insertSelective(room);
            }
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.createTaskRoom】新增施工模板任务房间信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 重新打开模板施工任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 10:53
     */
    @Override
    public ApiResponseEntity reopenTask(Integer id) throws AppException {
        try {
            // 调用涂鸦接口
            PaasConstructionTask task = taskMapper.selectByPrimaryKey(id);
            paasApiServiceInterface.call(PaasApiEnum.CONSTRUCTION_TASK_REOPEN,
                    BeanUtil.beanToMap(task, true));
            task.setStatus(RoomTaskStatusEnum.PENDING_CONSTRUCTION.toString());

            // 更新施工任务状态
            taskMapper.updateByPrimaryKeySelective(task);
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ConstructionTaskServiceImplements.reopenTask】重新打开模板施工任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.reopenTask】重新打开模板施工任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 验收施工任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 10:57
     */
    @Override
    public ApiResponseEntity acceptanceTask(Integer id) throws AppException {
        try {
            // 调用涂鸦接口
            PaasConstructionTask task = taskMapper.selectByPrimaryKey(id);
            paasApiServiceInterface.call(PaasApiEnum.CONSTRUCTION_TASK_ACCEPTANCE,
                    BeanUtil.beanToMap(task, true));
            task.setStatus(RoomTaskStatusEnum.ACCEPTANCE_COMPLETE.toString());

            // 更新施工任务状态
            taskMapper.updateByPrimaryKeySelective(task);

            // 同步房间设备信息
            this.synchroRoomDevice(task.getTaskId());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ConstructionTaskServiceImplements.acceptanceTask】验收施工任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.acceptanceTask】验收施工任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 同步房间设备信息
     * @Params: [task_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/8 11:11
     */
    private void synchroRoomDevice(String task_id) throws AppException {
        try {
            List<PaasRoomInfo> roomInfos = customizeTaskMapper.getTaskRoomInfo(task_id);
            for (PaasRoomInfo roomInfo : roomInfos) {
                deviceServiceInterface.synchroRoomDevice(roomInfo.getRoomId());
            }
        } catch (AppException e) {
            logger.error("【ConstructionTaskServiceImplements.synchroRoomDevice】同步房间设备信息异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.synchroRoomDevice】同步房间设备信息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工任务
     * @Params: [id]
     * @return: cn.xpms.system.framework.beans.entity.ApiResponseEntity
     * @Author: 柯雷
     * @Date: 2020/12/5 12:03
     */
    @Override
    public ApiResponseEntity deleteTask(Integer id) throws AppException {
        try {
            // 调用涂鸦接口
            PaasConstructionTask task = taskMapper.selectByPrimaryKey(id);
            paasApiServiceInterface.call(PaasApiEnum.DELETE_CONSTRUCTION_TASK,
                    BeanUtil.beanToMap(task, true));
            task.setValid(SystemConstants.INT_NO);

            // 更新施工任务
            taskMapper.updateByPrimaryKeySelective(task);
            // 删除施工任务房间
            this.deleteTaskRoom(task.getTaskId());
            return new ApiResponseEntity(SysErrorCode.SUCCESS);
        } catch (AppException e) {
            logger.error("【ConstructionTaskServiceImplements.deleteTask】删除施工任务异常：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.deleteTask】删除施工任务异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除施工任务房间
     * @Params: [task_id]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/5 12:06
     */
    private void deleteTaskRoom(String task_id) throws AppException {
        try {
            // 删除施工任务房间
            PaasConstructionTaskRoomExample example = new PaasConstructionTaskRoomExample();
            example.createCriteria().andTaskIdEqualTo(task_id);
            PaasConstructionTaskRoom room = new PaasConstructionTaskRoom();
            room.setValid(SystemConstants.INT_NO);
            taskRoomMapper.updateByExampleSelective(room, example);
        } catch (Exception e) {
            logger.error("【ConstructionTaskServiceImplements.deleteTaskRoom】删除施工任务房间异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}