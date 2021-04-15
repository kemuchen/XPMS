package cn.xpms.sso.bean.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName LoginEntity
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 13:47
 * @Version 1.0
 */
@ApiModel(
        value = "用户登录实体",
        description = "POJO, LoginEntity"
)
@Getter
@Setter
public class LoginEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录id", example = "admin")
    private String loginid;

    @ApiModelProperty(value = "登录密码", example = "a")
    private String password;
}
