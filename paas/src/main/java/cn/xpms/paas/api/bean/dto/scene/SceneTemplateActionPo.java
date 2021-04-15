package cn.xpms.paas.api.bean.dto.scene;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName SceneTemplateActionPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 10:01
 * @Version 1.0
 */
@ApiModel(
        value = "SceneTemplateActionPo",
        description = "PO, 场景动作PO"
)
@Getter
@Setter
public class SceneTemplateActionPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备名称", example = "")
    private String device_name;

    @ApiModelProperty(value = "执行动作参数列表", example = "")
    private JSONObject executor_property;

    @ApiModelProperty(value = "执行动作类别 设备", example = "dpIssue")
    private String action_executor = "dpIssue";

    @ApiModelProperty(value = "备注", example = "")
    private String memo;
}
