package cn.xpms.paas.api.bean.vo.template;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateArea;
import lombok.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ConstuctionTemplateAreaVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 16:18
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConstructionTemplateAreaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 区域信息 */
    PaasConstructionTemplateArea area;

    /** 位置列表 */
    List<ConstructionTemplatePositionVo> positions;
}