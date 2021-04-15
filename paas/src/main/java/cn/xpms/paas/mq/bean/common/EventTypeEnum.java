package cn.xpms.paas.mq.bean.common;

import cn.xpms.paas.mq.bean.body.*;

/**
 * @ClassName EventTypeEnum
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 14:40
 * @Version 1.0
 */
public enum EventTypeEnum {

    DEVICE_BIND("bind", "设备绑定", DeviceBindDataBodyVo.class),
    DEVICE_UNBIND("unbind", "设备解绑", DeviceBaseDataBodyVo.class),
    DEVICE_RESET("reset", "设备重置", DeviceBaseDataBodyVo.class),
    DEVICE_ONLINE("online", "设备上线", DeviceBaseDataBodyVo.class),
    DEVICE_OFFLINE("offline", "设备下线", DeviceBaseDataBodyVo.class),
    DEVICE_DP_REPORT("dp_report", "设备状态上报", DeviceDpReportDataBodyVo.class),
    DEVICE_NAME_UPDATE("name_update", "设备名称变更", DeviceBaseDataBodyVo.class),
    VOICE_NLU("voice_nlu", "语音识别结果推送", VoiceNluDataBodyVo.class),
    VOICE_ROOM_BIND("voice_room_bind", "音箱房间绑定结果推送", VoiceRoomBindDataBodyVo.class),
    TASK_STATUS_CHANGE("task_status_change", "任务状态变更推送", TaskStatusDataBodyVo.class);

    /** event_type */
    private String event_type;

    /** event_name */
    private String event_name;

    private Class clazz;

    EventTypeEnum(String event_type, String event_name, Class clazz) {
        this.event_type = event_type;
        this.event_name = event_name;
        this.clazz = clazz;
    }

    /**
     * @Description: 获取EventTypeEnum
     * @Params: [event_type]
     * @return: java.lang.Class
     * @Author: 柯雷
     * @Date: 2020/12/18 9:15
     */
    public static EventTypeEnum getEventType(String event_type) {
        for (EventTypeEnum typeEnum : EventTypeEnum.values()) {
            if (typeEnum.event_type.equals(event_type)) {
                return typeEnum;
            }
        }
        return null;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}