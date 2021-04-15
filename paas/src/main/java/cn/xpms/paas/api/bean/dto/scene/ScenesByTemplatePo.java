package cn.xpms.paas.api.bean.dto.scene;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ScenesByTemplatePo
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 14:56
 * @Version 1.0
 */
@ApiModel(
        value = "ScenesByTemplatePo",
        description = "PO, 根据模板批量添加场景PO"
)
@Getter
@Setter
public class ScenesByTemplatePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板ID", example = "")
    private Integer templateId;

    @ApiModelProperty(value = "场景名称", example = "")
    private String scene_name;

    @ApiModelProperty(value = "房间roomIds", example = "")
    private String[] roomIds;
}
