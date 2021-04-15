package cn.xpms.paas.api.bean.dto.room;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName TemplatePo
 * @Desc
 * @Author ycj
 * @Date 2021-01-08 10:34
 * @Version 1.0
 */
@ApiModel(
        value = "TemplatePo",
        description = "PO, 房间模板下发PO"
)
@Getter
@Setter
public class TemplatePo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房间模板ID", example = "")
    private Integer template_id;

    @ApiModelProperty(value = "下发房间唯一标识room_id集合", example = "")
    private String[] room_ids;
}
