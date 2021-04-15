package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @ClassName VoiceNluEntityVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 15:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoiceNluEntityVo implements Serializable {

    /** 参数值 */
    private String value;

    /** 参数名 */
    private String entity;
}