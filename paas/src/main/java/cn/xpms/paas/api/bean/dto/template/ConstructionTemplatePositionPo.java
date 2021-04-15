package cn.xpms.paas.api.bean.dto.template;

import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateInfraredDevice;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplatePosition;
import cn.xpms.paas.api.dao.generator.entity.PaasConstructionTemplateSplitDevice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ConstructionTemplatePositionPo
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/5 9:10
 * @Version 1.0
 */
@ApiModel(
        value = "ConstructionTemplatePositionPo",
        description = "PO, 施工模板位置"
)
@Getter
@Setter
public class ConstructionTemplatePositionPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "位置信息", example = "")
    PaasConstructionTemplatePosition position;

    @ApiModelProperty(value = "拆分设备列表", example = "")
    List<PaasConstructionTemplateSplitDevice> splitDevices;

    @ApiModelProperty(value = "红外设备列表", example = "")
    List<PaasConstructionTemplateInfraredDevice> infraredDevices;
}
