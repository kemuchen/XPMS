package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName TemplateConditionPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-29 15:33
 * @Version 1.0
 */
@ApiModel(
        value = "TemplateConditionPo",
        description = "PO, 自动化模板条件PO"
)
@Getter
@Setter
public class TemplateConditionPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "条件类型", example = "")
    private Integer entity_type = 1;

    @ApiModelProperty(value = "设备名称", example = "")
    private String device_name;

    @ApiModelProperty(value = "条件展示", example = "")
    private ConditionDisplayPo display;

    @ApiModelProperty(value = "条件排序 从1开始", example = "")
    private Integer order_num;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;
}
