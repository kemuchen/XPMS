package cn.xpms.paas.api.bean.dto.scene;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ExecutorPropertyPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 16:04
 * @Version 1.0
 */
@ApiModel(
        value = "ExecutorPropertyPo",
        description = "PO, 场景动作执行参数PO"
)
@Getter
@Setter
public class ExecutorPropertyPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数名称", example = "")
    private String key;

    @ApiModelProperty(value = "参数值", example = "")
    private String value;
}
