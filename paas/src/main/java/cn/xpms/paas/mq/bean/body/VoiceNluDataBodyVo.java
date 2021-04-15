package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName VoiceNluDataBodyVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 15:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoiceNluDataBodyVo implements Serializable {

    /** 设备id */
    private String devId;

    /** 命名空间 */
    private String namespace;

    /** 语音内容 */
    private List<VoiceNluVo> nlu;

    /** 产品id */
    private String productId;

    /** 用户id */
    private String uid;

    /** 唯一标识 */
    private String uuid;
}