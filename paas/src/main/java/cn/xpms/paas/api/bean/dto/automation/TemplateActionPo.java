package cn.xpms.paas.api.bean.dto.automation;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName TemplateActionPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 15:31
 * @Version 1.0
 */
@ApiModel(
        value = "TemplateActionPo",
        description = "PO, 自动化模板动作PO"
)
@Getter
@Setter
public class TemplateActionPo {

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
