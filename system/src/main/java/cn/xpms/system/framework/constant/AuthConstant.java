package cn.xpms.system.framework.constant;

/**
 * @ClassName AuthConstant
 * @Desc
 * @Author 柯雷
 * @Date 2020/10/28 14:06
 * @Version 1.0
 */
public class AuthConstant {

    /** 授权令牌 */
    public static String TOKEN = "USER_LOGIN_TOKEN";

    /** 登录uri */
    public static String LOGIN_URI = "/api/sso/login/login";

    /** 退出uri */
    public static String LOGOUT_URI = "/api/sso/login/logout";

    /** 校验是否登录uri */
    public static String CHECK_LOGIN_URI = "/api/sso/login/check/";
}