package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ConditionDisplayPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 16:32
 * @Version 1.0
 */
@ApiModel(
        value = "ConditionDisplayPo",
        description = "PO, 自动化条件条件展示PO"
)
@Getter
@Setter
public class ConditionDisplayPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备状态编码类型", example = "")
    private String type;

    @ApiModelProperty(value = "设备状态编码", example = "")
    private String code;

    @ApiModelProperty(value = "操作符", example = "")
    private String operator;

    @ApiModelProperty(value = "状态值", example = "")
    private Object value;
}
