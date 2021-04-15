package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ConditionPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 16:27
 * @Version 1.0
 */
@ApiModel(
        value = "ConditionPo",
        description = "PO, 自动化条件PO"
)
@Getter
@Setter
public class ConditionPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "条件类型", example = "")
    private Integer entity_type = 1;

    @ApiModelProperty(value = "执行动作实体 entity_type=1时即设备ID", example = "")
    private String entity_id;

    @ApiModelProperty(value = "条件展示", example = "")
    private ConditionDisplayPo display;

    @ApiModelProperty(value = "条件排序 从1开始", example = "")
    private Integer order_num;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;
}
