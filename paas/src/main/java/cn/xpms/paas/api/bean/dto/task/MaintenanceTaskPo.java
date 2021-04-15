package cn.xpms.paas.api.bean.dto.task;

import cn.xpms.paas.api.dao.generator.entity.PaasMaintenanceTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MaintenanceTaskVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 15:03
 * @Version 1.0
 */
@ApiModel(
        value = "MaintenanceTaskPo",
        description = "PO, 维修任务"
)
@Getter
@Setter
public class MaintenanceTaskPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "维修任务信息", example = "")
    PaasMaintenanceTask paasMaintenanceTask;

    @ApiModelProperty(value = "房间设备对应关系", example = "")
    List<MaintenanceTaskRoomDeviceRelationVo> roomDeviceRelations;
}