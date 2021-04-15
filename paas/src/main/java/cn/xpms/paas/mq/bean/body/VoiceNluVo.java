package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName VoiceNluVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 15:04
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoiceNluVo implements Serializable {

    /** 实体列表 */
    private List<VoiceNluEntityVo> entities;

    /** 域 */
    private String domain;

    /** 语音内容 */
    private String text;

    /** 语音意图 */
    private String intent;

    /** 配置id */
    private Integer confidenance;
}