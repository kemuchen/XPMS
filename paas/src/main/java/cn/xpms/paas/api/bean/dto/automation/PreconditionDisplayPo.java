package cn.xpms.paas.api.bean.dto.automation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName PreconditionDisplayPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-16 16:38
 * @Version 1.0
 */
@ApiModel(
        value = "PreconditionDisplayPo",
        description = "PO, 自动化前置条件条件展示PO"
)
@Getter
@Setter
public class PreconditionDisplayPo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "开始时间 24小时制", example = "10:00")
    private String start;

    @ApiModelProperty(value = "结束时间 24小时制", example = "22:00")
    private String end;

    @ApiModelProperty(value = "由0和1组成的7位数 0表示不执行 1表示执行 第一位为周日依次到周六", example = "0011000 为每周二周三执行")
    private String loops;

    @ApiModelProperty(value = "时区id", example = "")
    private String timezone_id="Asia/Shanghai";
}
