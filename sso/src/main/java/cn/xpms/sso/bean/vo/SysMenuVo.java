package cn.xpms.sso.bean.vo;

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
 * @Date 2020/11/26 9:21
 * @Version 1.0
 */
@ApiModel(
        value = "SysMenuVo",
        description = "VO, 菜单权限查结果实体"
)
@Getter
@Setter
public class SysMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "访问路径", example = "/overview")
    private String path;

    @ApiModelProperty(value = "菜单名称", example = "概览")
    private String name;

    @ApiModelProperty(value = "子菜单", example = "")
    private List<SysMenuVo> routes;
}