package cn.xpms.paas.mq.bean.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @ClassName TaskStatusDataBodyVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/17 15:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskStatusDataBodyVo implements Serializable {

    /** 任务id */
    private String taskId;

    /** 命名空间 */
    private String namespace;

    /** 任务类型 */
    private String taskType;

    /** 任务状态 */
    private String taskStatus;

    /** 推送时间戳 */
    private Long sendTime;
}