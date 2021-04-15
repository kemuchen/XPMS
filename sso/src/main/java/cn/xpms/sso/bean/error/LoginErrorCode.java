package cn.xpms.sso.bean.error;

import cn.xpms.system.framework.beans.error.BaseErrorCode;

/**
 * @ClassName LoginErrorCode
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 10:36
 * @Version 1.0
 */
public enum LoginErrorCode implements BaseErrorCode {

    /** 登录成功 */
    LOGIN_SUCCESS("00100", "登录成功", "登录成功"),

    /** 用户不存在或密码错误 */
    LOGIN_ID_ERROR("00101", "用户不存在或密码错误", "用户不存在或密码错误"),

    /** 用户被锁定 */
    USERNAME_LOCKED("00102", "用户被锁定", "用户被锁定"),

    /** 多点登录 */
    MORE_THEN_ONE_LOGIN("00105", "已在其他地方登录", "已在其他地方登录");

    /**
     * 错误代码
     */
    private String error_code;

    /**
     * 错误描述
     */
    private String error_desc;

    /**
     * 前台提示
     */
    private String tip;

    /**
     * @return
     * @Description 构造方法
     * @Date 16:32 2020/3/28
     * @Param [error_code, error_desc]
     **/
    LoginErrorCode(String error_code, String error_desc, String tip) {
        this.error_code = error_code;
        this.error_desc = error_desc;
        this.tip = tip;
    }

    @Override
    public String getError_code() {
        return this.error_code;
    }

    @Override
    public String getError_desc() {
        return this.error_desc;
    }

    @Override
    public String getTip() {
        return this.error_desc;
    }
}
