package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @ClassName DeviceBindDataBodyVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 14:49
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceBindDataBodyVo implements Serializable {

    /** 设备id */
    private String devId;

    /** 设备名称 */
    private String deviceName;

    /** 设备id */
    private String gwId;

    /** 设备位置 */
    private Object location;

    /** 命名空间 */
    private String namespace;

    /** 拥有者id */
    private String owerId;

    /** 位置 */
    private String position;

    /** 产品id */
    private String productId;

    /** 是否子设备 */
    private Boolean sub;

    /** 用户id */
    private String uid;

    /** 唯一标识 */
    private String uuid;
}