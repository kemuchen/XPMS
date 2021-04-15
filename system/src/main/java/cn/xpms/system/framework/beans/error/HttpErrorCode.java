package cn.xpms.system.framework.beans.error;

/**
 * @ClassName HttpErrorCode
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-28 15:32
 * @Version 1.0
 */
public enum HttpErrorCode implements BaseErrorCode {

    /** 服务不存在 */
    ERROR_404("00200", "访问的服务器地址不存在", "服务不存在"),

    /** 禁止访问 */
    ERROR_403("00201", "服务器禁止访问", "禁止访问"),

    /** 服务器异常 */
    ERROR_500("00202", "服务器抛出运行时异常", "服务器异常"),

    /** 未知错误 */
    ERROR_000("00203", "未知错误", "未知错误"),

    /** 服务器连接失败 */
    ERROR_UNCONECTED("00204", "服务器连接失败", "服务器连接失败");

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
     * @Description: 是否包含错误代码
     * @Params: [error_code]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020-04-28 15:39
     */
    public static boolean contains(String error_code) {
        for (HttpErrorCode httpErrorCode : HttpErrorCode.values()) {
            if (httpErrorCode.getError_code().equals(error_code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Description: 获取错误代码
     * @Params: [error_code]
     * @return: cn.framework.security.exception.codes.HttpErrorCode
     * @Author: 柯雷
     * @Date: 2020-04-28 15:42
     */
    public static HttpErrorCode getHttpErrorCode(String error_code) {
        for (HttpErrorCode httpErrorCode : HttpErrorCode.values()) {
            if (httpErrorCode.getError_code().equals(error_code)) {
                return httpErrorCode;
            }
        }
        return null;
    }

    HttpErrorCode(String error_code, String error_desc, String tip) {
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
