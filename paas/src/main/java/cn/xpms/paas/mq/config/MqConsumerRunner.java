package cn.xpms.paas.mq.config;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.dao.generator.entity.*;
import cn.xpms.paas.api.dao.generator.repository.*;
import cn.xpms.paas.api.service.business.inter.automation.CustomAutomationServiceInterface;
import cn.xpms.paas.api.util.PaasConfigUtil;
import cn.xpms.paas.mq.bean.body.DeviceBaseDataBodyVo;
import cn.xpms.paas.mq.bean.body.DeviceDpReportDataBodyVo;
import cn.xpms.paas.mq.bean.body.DeviceStatusVo;
import cn.xpms.paas.mq.bean.body.TaskStatusDataBodyVo;
import cn.xpms.paas.mq.bean.common.*;
import cn.xpms.paas.mq.util.AESBase64Utils;
import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MqConsumerRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 15:17
 * @Version 1.0
 */
//@Component
//@Order
public class MqConsumerRunner implements ApplicationRunner {

    /**
     * 日志打印对象
     */
    Logger logger = LoggerFactory.getLogger(MqConsumerRunner.class);

    /**
     * 保存推送消息信息
     */
    @Autowired
    PaasPlusarMessageMapper paasPlusarMessageMapper;

    /**
     * 设备管理CURD
     */
    @Autowired
    PaasDeviceInfoMapper paasDeviceInfoMapper;

    /**
     * 设备最新状态CURD
     */
    @Autowired
    PaasDeviceInfoLatestStatusMapper deviceInfoLatestStatusMapper;

    /**
     * 施工任务CURD
     */
    @Autowired
    PaasConstructionTaskMapper paasConstructionTaskMapper;

    /**
     * 维修任务CURD
     */
    @Autowired
    PaasMaintenanceTaskMapper paasMaintenanceTaskMapper;

    @Autowired
    CustomAutomationServiceInterface customAutomationService;

    /**
     * @Description: 接收消息推送
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/17 15:21
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // accessId配置
        String accessId = PaasConfigUtil.getPassConfigValue(Constant.ACCESS_ID);
        // accessKey配置
        String accessKey = PaasConfigUtil.getPassConfigValue(Constant.ACCESS_KEY);
        // 监听消息推送
        MqConsumer mqConsumer = MqConsumer.build().serviceUrl(MqConfigs.CN_SERVER_URL).
                accessId(accessId).accessKey(accessKey)
                .maxRedeliverCount(3).messageListener(message -> {
                            this.hanldeMessage(new String(message.getData()), accessKey);
                        }
                );
        mqConsumer.start();
    }

    /**
     * @Description: 处理消息推送
     * @Params: [message]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/17 15:25
     */
    private void hanldeMessage(String message, String accessKey) throws AppException {
        try {
            PaasMessageVO vo = JSON.parseObject(message, PaasMessageVO.class);
            // 非Paas平台推送消息，则不处理
            if (!SysUtil.isEmpty(vo.getEncryptPayload())) {
                // 消息解密
                String decrypt = AESBase64Utils.decrypt(vo.getEncryptPayload(), accessKey.substring(8, 24));

                // 解析消息体数据
                ObjectMapper json = new ObjectMapper();
                json.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                PaasMessageBodyVo paasMessageBodyVo = json.readValue(decrypt, PaasMessageBodyVo.class);
                paasMessageBodyVo.setData(json.readValue(paasMessageBodyVo.getData().toString(),
                        EventTypeEnum.getEventType(paasMessageBodyVo.getEventType()).getClazz()));

                // 处理消息
                this.hanldeMessage(paasMessageBodyVo);

                // 保存消息推送记录
                this.saveMessage(paasMessageBodyVo, vo);
            }
        } catch (Exception e) {
            logger.error("【MqConsumerRunner.hanldeMessage】推送消息处理异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 处理消息
     * @Params: [paasMessageBodyVo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/17 15:37
     */
    private void hanldeMessage(PaasMessageBodyVo paasMessageBodyVo) throws AppException {
        try {
            EventTypeEnum eventTypeEnum = EventTypeEnum.getEventType(paasMessageBodyVo.getEventType());
            switch (eventTypeEnum) {
                case DEVICE_ONLINE:
                    this.saveDeviceLine((DeviceBaseDataBodyVo) paasMessageBodyVo.getData(), SystemConstants.STR_TRUE);
                    break;
                case DEVICE_OFFLINE:
                    this.saveDeviceLine((DeviceBaseDataBodyVo) paasMessageBodyVo.getData(), SystemConstants.STR_FALSE);
                    break;
                case DEVICE_DP_REPORT:
                    this.saveDeviceStatus((DeviceDpReportDataBodyVo) paasMessageBodyVo.getData());
                    break;
                case TASK_STATUS_CHANGE:
                    this.updateTaskStatus((TaskStatusDataBodyVo) paasMessageBodyVo.getData());
                    break;
            }
        } catch (Exception e) {
            logger.error("【MqConsumerRunner.hanldeMessage】处理消息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 更新施工任务状态
     * @Params: [taskStatusDataBodyVo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2021/1/4 11:47
     */
    private void updateTaskStatus(TaskStatusDataBodyVo taskStatusDataBodyVo) throws AppException {
        try {
            if (TaskTypeEnum.CONSTRUCTION.toString().equals(taskStatusDataBodyVo.getTaskType())) {
                // 更新施工任务状态
                PaasConstructionTask task = new PaasConstructionTask();
                task.setStatus(taskStatusDataBodyVo.getTaskStatus());
                PaasConstructionTaskExample example = new PaasConstructionTaskExample();
                example.createCriteria().andTaskIdEqualTo(taskStatusDataBodyVo.getTaskId());
                paasConstructionTaskMapper.updateByExampleSelective(task, example);
            } else if (TaskTypeEnum.MAINTENANCE.toString().equals(taskStatusDataBodyVo.getTaskType())) {
                // 更新维修任务施工状态
                PaasMaintenanceTask task = new PaasMaintenanceTask();
                task.setStatus(taskStatusDataBodyVo.getTaskStatus());
                PaasMaintenanceTaskExample example = new PaasMaintenanceTaskExample();
                example.createCriteria().andTaskIdEqualTo(taskStatusDataBodyVo.getTaskId());
                paasMaintenanceTaskMapper.updateByExampleSelective(task, example);
            }
        } catch (Exception e) {
            logger.error("【MqConsumerRunner.updateTaskStatus】更新施工任务状态异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存设备是否在线状态
     * @Params: [dataBodyVo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/18 11:13
     */
    private void saveDeviceLine(DeviceBaseDataBodyVo dataBodyVo, String online) throws AppException {
        try {
            PaasDeviceInfo paasDeviceInfo = new PaasDeviceInfo();
            paasDeviceInfo.setOnline(online);
            PaasDeviceInfoExample example = new PaasDeviceInfoExample();
            example.createCriteria().andDeviceIdEqualTo(dataBodyVo.getDevId());
            paasDeviceInfoMapper.updateByExampleSelective(paasDeviceInfo, example);
        } catch (Exception e) {
            logger.error("【MqConsumerRunner.saveDeviceLine】保存设备是否在线状态异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存设备状态
     * @Params: [deviceStatusVo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/18 11:13
     */
    private void saveDeviceStatus(DeviceDpReportDataBodyVo dpReportDataBodyVo) throws AppException {
        try {
            PaasDeviceInfoLatestStatus latestStatus = new PaasDeviceInfoLatestStatus();
            // 遍历更新原有状态均为非最新
            PaasDeviceInfoLatestStatusExample example = new PaasDeviceInfoLatestStatusExample();
            List<String> codes = new ArrayList<>();
            for (DeviceStatusVo statusVo : dpReportDataBodyVo.getStatus()) {
                codes.add(statusVo.getCode());
            }
            example.createCriteria().andDeviceIdEqualTo(dpReportDataBodyVo.getDevId())
                    .andCodeIn(codes).andLatestEqualTo(SystemConstants.INT_YES);
            latestStatus.setLatest(SystemConstants.INT_NO);
            deviceInfoLatestStatusMapper.updateByExampleSelective(latestStatus, example);

            latestStatus.setDeviceId(dpReportDataBodyVo.getDevId());
            // 并新增最新状态
            for (DeviceStatusVo statusVo : dpReportDataBodyVo.getStatus()) {
                latestStatus.setId(null);
                latestStatus.setCode(statusVo.getCode());
                latestStatus.setValue(statusVo.getValue());
                latestStatus.setLatest(SystemConstants.INT_YES);
                deviceInfoLatestStatusMapper.insertSelective(latestStatus);

                customAutomationService.triggerCustomAutomation(dpReportDataBodyVo.getDevId(), statusVo.getCode(), statusVo.getValue());
            }

        } catch (Exception e) {
            logger.error("【MqConsumerRunner.saveDeviceStatus】保存设备状态异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 保存消息
     * @Params: [paasMessageBodyVo]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/12/17 15:37
     */
    private void saveMessage(PaasMessageBodyVo paasMessageBodyVo, PaasMessageVO vo) throws AppException {
        try {
            ObjectMapper json = new ObjectMapper();
            json.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PaasPlusarMessage paasPlusarMessage = new PaasPlusarMessage();
            paasPlusarMessage.setBizCode(paasMessageBodyVo.getBizCode());
            paasPlusarMessage.setBizName(BizCodeEnum.getBizCode(paasMessageBodyVo.getBizCode()).getBiz_name());
            paasPlusarMessage.setEventType(paasMessageBodyVo.getEventType());
            paasPlusarMessage.setEventName(EventTypeEnum.getEventType(paasMessageBodyVo.getEventType()).getEvent_name());
            paasPlusarMessage.setData(json.writeValueAsString(paasMessageBodyVo.getData()));
            paasPlusarMessage.setEncryptType(vo.getEncryptType());
            paasPlusarMessage.setSign(vo.getSign());
            paasPlusarMessage.setV(vo.getV());
            paasPlusarMessage.setT(vo.getT().toString());
            paasPlusarMessageMapper.insertSelective(paasPlusarMessage);
        } catch (Exception e) {
            logger.error("【MqConsumerRunner.saveMessage】保存消息异常：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}