package cn.xpms.paas.api.bean.dto.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName PaasDeviceQuery
 * @Desc
 * @Author ycj
 * @Date 2021-01-27 15:01
 * @Version 1.0
 */
@ApiModel(
        value = "PaasDeviceQuery",
        description = "PO, 设备查询实体"
)
@Getter
@Setter
public class PaasDeviceQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目project_id", example = "")
    private String projectId;

    @ApiModelProperty(value = "房间room_id", example = "")
    private String roomId;

    @ApiModelProperty(value = "房间room_no", example = "")
    private String roomNo;

    @ApiModelProperty(value = "设备类型编码", example = "")
    private String categoryCode;

    @ApiModelProperty(value = "是否在线", example = "")
    private String online;

    @ApiModelProperty(value = "是否支持场景", example = "")
    private Integer scene;

    @ApiModelProperty(value = "是否支持自动化条件", example = "")
    private Integer condition;

    @ApiModelProperty(value = "是否支持自动化动作", example = "")
    private Integer action;
}
