package cn.xpms.paas.api.bean.dto.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ProjectPo
 * @Desc
 * @Author ycj
 * @Date 2020-12-15 9:44
 * @Version 1.0
 */
@ApiModel(
        value = "ProjectPo",
        description = "PO, 项目PO"
)
@Getter
@Setter
public class PaasProjectPo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目主键", example = "")
    private Integer id;

    @ApiModelProperty(value = "绑定酒店ID", example = "")
    private Integer hotel_id;

    @ApiModelProperty(value = "项目projectId", example = "")
    private String projectId;

    private String locationId;

    @ApiModelProperty(value = "项目名称", example = "")
    private String projectName;

    private String address;

    private String timeZoneId;

    private String status;

    private String auth;

    private String memo;
}
