package cn.xpms.paas.api.bean.vo.template;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplate;
import lombok.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TemplateVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/2 16:13
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConstructionTemplateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 模板信息 */
    private PaasConstructionTemplate template;

    /** 区域列表 */
    private List<ConstructionTemplateAreaVo> areas;
}