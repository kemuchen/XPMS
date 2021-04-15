package cn.xpms.paas.api.bean.vo.device;

import cn.xpms.paas.api.bean.annotation.PaasApiPageData;
import cn.xpms.paas.api.bean.dto.common.PaasApiPageResultVo;
import cn.xpms.paas.api.dao.generator.entity.PaasDeviceInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @ClassName PaasDeviceInfoPageResultVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/16 10:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaasDeviceInfoPageResultVo extends PaasApiPageResultVo {

    /** 设备参数 */
    @PaasApiPageData(field_name = "devices")
    private List<PaasDeviceInfo> devices;
}
