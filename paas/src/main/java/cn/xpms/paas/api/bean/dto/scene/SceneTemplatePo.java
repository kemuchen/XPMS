package cn.xpms.paas.api.bean.dto.scene;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName SceneTemplatePo
 * @Desc
 * @Author ycj
 * @Date 2020-12-28 09:59
 * @Version 1.0
 */
@ApiModel(
        value = "SceneTemplatePo",
        description = "PO, 场景PO"
)
@Getter
@Setter
public class SceneTemplatePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "")
    private Integer id;

    @ApiModelProperty(value = "项目project_id", example = "")
    private String project_id;

    @ApiModelProperty(value = "模板名称", example = "")
    private String template_name;

    @ApiModelProperty(value = "场景名称", example = "")
    private String scene_name;

    @ApiModelProperty(value = "动作列表", example = "")
    private List<SceneTemplateActionPo> actions;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;

}
