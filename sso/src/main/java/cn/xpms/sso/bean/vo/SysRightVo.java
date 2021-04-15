package cn.xpms.sso.bean.vo;

import cn.xpms.system.system.dao.generator.entity.SysRight;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysMenuVo
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/26 9:22
 * @Version 1.0
 */
@ApiModel(
        value = "SysRightVo",
        description = "VO, 权限查结果"
)
@Getter
@Setter
public class SysRightVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "路由权限", example = "")
    private List<SysRight> authRouters;

    @ApiModelProperty(value = "菜单权限", example = "")
    private List<SysMenuVo> menus;
}
