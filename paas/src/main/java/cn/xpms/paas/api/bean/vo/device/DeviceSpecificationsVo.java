package cn.xpms.paas.api.bean.vo.device;

import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfoFunction;
import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DeviceSpecificationsVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/8 10:37
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceSpecificationsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 设备品类 */
    private String category;

    /** 设备品类功能集 */
    private List<PaasDeviceInfoFunction> functions;

    /** 设备状态集 */
    private List<PaasDeviceInfoStatus> status;
}
