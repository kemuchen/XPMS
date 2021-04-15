package cn.xpms.paas.api.bean.vo.task;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTask;
import cn.xpms.paas.api.dao.generator.entity.PaasRoomInfo;
import lombok.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ConstructionTemplateTaskVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/4 9:36
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConstructionTemplateTaskVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 施工任务信息 */
    private PaasConstructionTask task;

    /** 施工房间列表 */
    List<PaasRoomInfo> rooms;
}