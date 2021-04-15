package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName CustomAutomationByTemplatePo
 * @Desc
 * @Author ycj
 * @Date 2021-01-18 10:18
 * @Version 1.0
 */
@ApiModel(
        value = "CustomAutomationByTemplatePo",
        description = "PO, 根据模板批量添加自定义自动化PO"
)
@Getter
@Setter
public class CustomAutomationByTemplatePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板ID", example = "")
    private Integer templateId;

    @ApiModelProperty(value = "自动化名称", example = "")
    private String automation_name;

    @ApiModelProperty(value = "房间roomIds", example = "")
    private String[] roomIds;
}
