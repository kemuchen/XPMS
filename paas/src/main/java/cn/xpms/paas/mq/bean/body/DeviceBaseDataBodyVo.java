package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName DeviceUnBindDataBodyVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 14:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceBaseDataBodyVo implements Serializable {

    /** 设备id */
    private String devId;

    /** 设备名称 */
    private String deviceName;

    /** 命名空间 */
    private String namespace;

    /** 时间戳 */
    private Long time;
}