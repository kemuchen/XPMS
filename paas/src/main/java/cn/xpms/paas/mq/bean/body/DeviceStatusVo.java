package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName DeviceStatusVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 14:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceStatusVo implements Serializable {

    /** 代码 */
    private String code;

    /** 值 */
    private String value;

    /** 时间戳 */
    private Long time;
}