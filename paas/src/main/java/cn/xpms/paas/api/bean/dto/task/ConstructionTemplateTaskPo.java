package cn.xpms.paas.api.bean.dto.task;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(
        value = "ConstructionTemplateTaskPo",
        description = "PO, 施工模板位置"
)
@Getter
@Setter
public class ConstructionTemplateTaskPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "施工任务信息", example = "")
    private PaasConstructionTask task;

    @ApiModelProperty(value = "施工房间列表", example = "")
    List<String> room_ids;
}