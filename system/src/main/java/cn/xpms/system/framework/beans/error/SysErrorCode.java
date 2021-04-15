package cn.xpms.system.framework.beans.error;

/**
 * @author ：柯雷
 * @ClassName:：ErrorEnum
 * @Description：
 * @date ：2020/3/28 16:25
 */
public enum SysErrorCode implements BaseErrorCode {

    /** 执行成功 */
    SUCCESS("00000", "操作成功", "操作成功"),

    /** 系统异常 */
    SYSTEM_ERROR("00001", "系统程序报错", "系统异常"),

    /** 业务校验异常 */
    SERVICE_CHECK_ERROR("00002", "业务校验不通过", "业务校验不通过"),

    /** 子系统不存在 */
    SUBSYS_NOT_EXIST("00003", "子系统不存在", "子系统不存在"),

    /** 子系统不存在 */
    UN_LOGIN("00004", "未登录", "未登录"),

    /** 参数校验异常 */
    PARAMS_CHECK_ERROR("00002", "业务校验不通过", "业务校验不通过");

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
    SysErrorCode(String error_code, String error_desc, String tip) {
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
        return this.tip;
    }
}
