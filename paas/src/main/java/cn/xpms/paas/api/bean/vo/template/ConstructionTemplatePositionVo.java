package cn.xpms.paas.api.bean.vo.template;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplatePosition;
import lombok.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ConstructTemplatePositionVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 16:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConstructionTemplatePositionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 位置信息 */
    PaasConstructionTemplatePosition position;

    /** 拆分设备列表 */
    List<PositionSplitDeviceVo> splitDevices;

    /** 红外设备列表 */
    List<PositionInfraredDeviceVo> infraredDevices;
}