package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName AutomationPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 16:26
 * @Version 1.0
 */
@ApiModel(
        value = "AutomationPo",
        description = "PO, 自动化PO"
)
@Getter
@Setter
public class AutomationPo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "")
    private Integer id;

    @ApiModelProperty(value = "房间唯一标识", example = "")
    private String room_id;

    @ApiModelProperty(value = "自动化唯一标识", example = "")
    private String automation_id;

    @ApiModelProperty(value = "自动化名称", example = "")
    private String automation_name;

    @ApiModelProperty(value = "条件列表", example = "")
    private List<ConditionPo> conditions;

    @ApiModelProperty(value = "前置条件列表", example = "")
    private List<PreconditionPo> preconditions;

    @ApiModelProperty(value = "动作列表", example = "")
    private List<ActionPo> actions;

    @ApiModelProperty(value = "匹配类型 1任意条件满足 2全部条件满足 3自定义条件触发 需设置condition_rule", example = "")
    private Integer match_type;

    @ApiModelProperty(value = "自定义条件规则", example = "")
    private String condition_rule;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;
}
