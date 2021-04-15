package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName VoiceRoomBindDataBodyVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 15:08
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoiceRoomBindDataBodyVo implements Serializable {

    /** 音箱id */
    private String voiceId;

    /** 命名空间 */
    private String namespace;
}
