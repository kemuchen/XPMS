package cn.xpms.paas.api.bean.dto.automation;

import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationCondition;
import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationScene;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName CustomAutomationPo
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 10:47
 * @Version 1.0
 */
@ApiModel(
        value = "CustomAutomationPo",
        description = "PO, 自定义自动化PO"
)
@Getter
@Setter
public class CustomAutomationPo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "")
    private Integer id;

    @ApiModelProperty(value = "房间唯一标识", example = "")
    private String room_id;

    @ApiModelProperty(value = "自动化类型", example = "")
    private Integer automation_type_id;

    @ApiModelProperty(value = "自动化名称", example = "")
    private String automation_name;

    @ApiModelProperty(value = "条件列表", example = "")
    private List<PaasCustomAutomationCondition> conditions;

    @ApiModelProperty(value = "场景列表", example = "")
    private List<PaasCustomAutomationScene> scenes;

    @ApiModelProperty(value = "匹配类型 1任意条件满足 2全部条件满足 3自定义条件触发 需设置condition_rule", example = "")
    private Integer match_type;

    @ApiModelProperty(value = "自定义条件规则", example = "")
    private String condition_rule;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;
}
