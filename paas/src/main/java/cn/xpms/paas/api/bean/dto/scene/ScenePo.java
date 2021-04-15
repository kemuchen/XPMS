package cn.xpms.paas.api.bean.dto.scene;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName ScenePo
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 15:59
 * @Version 1.0
 */
@ApiModel(
        value = "ScenePo",
        description = "PO, 场景PO"
)
@Getter
@Setter
public class ScenePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "")
    private Integer id;

    @ApiModelProperty(value = "房间唯一标识", example = "")
    private String room_id;

    @ApiModelProperty(value = "场景名称", example = "")
    private String scene_name;

    @ApiModelProperty(value = "场景唯一标识", example = "")
    private String scene_id;

    @ApiModelProperty(value = "动作列表", example = "")
    private List<ActionPo> actions;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;

}
