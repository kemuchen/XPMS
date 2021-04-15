package cn.xpms.paas.api.bean.vo.task;

import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfo;
import cn.xpms.paas.api.dao.generator.entity.PaasMaintenanceTaskRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName MaintenanceTaskRoomVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/7 15:40
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceTaskRoomVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 维修任务信息 */
    private PaasMaintenanceTaskRoom room;

    /** 维修房间设备列表 */
    List<PaasDeviceInfo> room_device_info;
}
