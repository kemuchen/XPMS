package cn.xpms.paas.api.bean.dto.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MaintenanceTaskRoomVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 14:54
 * @Version 1.0
 */
@ApiModel(
        value = "MaintenanceTaskRoomDeviceRelationVo",
        description = "PO, 维修任务房间设备关系列表"
)
@Getter
@Setter
public class MaintenanceTaskRoomDeviceRelationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房间唯一标识", example = "")
    private String room_id;

    @ApiModelProperty(value = "设备唯一标识列表", example = "")
    private List<String> device_ids;
}