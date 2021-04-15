package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName AutomationByTemplatePo
 * @Desc
 * @Author ycj
 * @Date 2020-12-31 9:27
 * @Version 1.0
 */
@ApiModel(
        value = "AutomationByTemplatePo",
        description = "PO, 根据模板批量添加自动化PO"
)
@Getter
@Setter
public class AutomationByTemplatePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板ID", example = "")
    private Integer templateId;

    @ApiModelProperty(value = "自动化名称", example = "")
    private String automation_name;

    @ApiModelProperty(value = "房间roomIds", example = "")
    private String[] roomIds;
}
