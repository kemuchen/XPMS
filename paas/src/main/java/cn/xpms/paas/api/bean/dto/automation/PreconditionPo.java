package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName PreconditionPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 16:35
 * @Version 1.0
 */
@ApiModel(
        value = "PreconditionPo",
        description = "PO, 自动化前置条件PO"
)
@Getter
@Setter
public class PreconditionPo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "暂支持时间段限制 固定为timeCheck", example = "")
    private String cond_type = "timeCheck";

    @ApiModelProperty(value = "条件展示", example = "")
    private PreconditionDisplayPo display;

    @ApiModelProperty(value = "备注", example = "")
    private String memo;
}
