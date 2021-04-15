package cn.xpms.paas.api.bean.dto.scene;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName TemplateScenesPo
 * @Desc
 * @Author ycj
 * @Date 2021-02-07 10:57
 * @Version 1.0
 */
@ApiModel(
        value = "TemplateScenesPo",
        description = "PO, 解除模板场景关联(删除场景)PO"
)
@Getter
@Setter
public class TemplateScenesPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板ID", example = "")
    private Integer templateId;

    @ApiModelProperty(value = "场景sceneIds", example = "")
    private String[] sceneIds;
}
