package cn.xpms.paas.mq.bean.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName MessageVO
 * @Desc paas平台消息推送VO
 * @Author 柯雷
 * @Date 2020/11/17 9:13
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaasMessageVO implements Serializable {

    /** api版本号 */
    private String v;

    /** 签名 */
    private String sign;

    /** 时间戳 */
    private Long t;

    /** 加密数据 */
    private String encryptPayload;

    /** 加密方式 */
    private String encryptType;
}