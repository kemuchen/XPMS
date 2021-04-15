package cn.xpms.paas.api.bean.dto.automation;

import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationTemplateCondition;
import cn.xpms.paas.api.dao.generator.entity.PaasCustomAutomationTemplateScene;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName CustomAutomationTemplatePo
 * @Desc
 * @Author ycj
 * @Date 2021-01-12 16:48
 * @Version 1.0
 */
@ApiModel(
        value = "CustomAutomationTemplatePo",
        description = "PO, 自定义自动化模板PO"
)
@Getter
@Setter
public class CustomAutomationTemplatePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "")
    private Integer id;

    @ApiModelProperty(value = "项目唯一标识", example = "")
    private String project_id;

    @ApiModelProperty(value = "模板房间唯一标识", example = "")
    private String room_id;

    @ApiModelProperty(value = "自动化类型", example = "")
    private Integer automation_type_id;

    @ApiModelProperty(value = "自动化模板名称", example = "")
    private String template_name;

    @ApiModelProperty(value = "条件列表", example = "")
    private List<PaasCustomAutomationTemplateCondition> conditions;

    @ApiModelProperty(value = "场景列表", example = "")
    private List<PaasCustomAutomationTemplateScene> scenes;

    @ApiModelProperty(value = "匹配类型 1任意条件满足 2全部条件满足 3自定义条件触发 需设置condition_rule", example = "")
    private Integer match_type;

    @ApiModelProperty(value = "自定义条件规则", example = "")
    private String condition_rule;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;

}
