package cn.xpms.paas.mq.bean.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName PaasMessageBodyVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 14:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaasMessageBodyVo<T> implements Serializable {

    /** 业务编号 */
    private String bizCode;

    /** 事件类型 */
    private String eventType;

    /** 消息数据 */
    private T data;
}