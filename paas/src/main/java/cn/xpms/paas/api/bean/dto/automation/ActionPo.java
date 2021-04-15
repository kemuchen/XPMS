package cn.xpms.paas.api.bean.dto.automation;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ActionPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 16:46
 * @Version 1.0
 */
@ApiModel(
        value = "ActionPo",
        description = "PO, 自动化动作PO"
)
@Getter
@Setter
public class ActionPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "执行动作实体 即设备ID", example = "")
    private String entity_id;

    @ApiModelProperty(value = "执行动作参数列表", example = "")
    private JSONObject executor_property;

    @ApiModelProperty(value = "执行动作类别 设备", example = "dpIssue")
    private String action_executor = "dpIssue";

    @ApiModelProperty(value = "备注", example = "")
    private String memo;
}
