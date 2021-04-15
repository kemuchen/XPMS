package cn.xpms.paas.api.bean.error;

import cn.xpms.system.framework.beans.error.BaseErrorCode;

/**
 * @ClassName PaasApiErrorCode
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:05
 * @Version 1.0
 */
public enum PaasApiErrorCode implements BaseErrorCode {

    INTER_CONFIG_ERR("20001", "涂鸦工程施工接口配置异常", "涂鸦工程施工接口配置异常"),

    PARAMS_ERROR("20002", "传入参数异常，缺少必传参数", "缺少必传参数"),

    INTER_RESULT_ERROR("20003", "涂鸦接口返回异常", ""),

    NO_SYS_CONFIG("20004", "缺少系统配置", "缺少系统配置:");

    /** 错误代码 */
    private String error_code;

    /** 错误描述 */
    private String error_desc;

    /** 前台提示 */
    private String tip;

    PaasApiErrorCode(String error_code, String error_desc, String tip) {
        this.error_code = error_code;
        this.error_desc = error_desc;
        this.tip = tip;
    }

    public PaasApiErrorCode setTip(String tip) {
        this.tip = this.tip + tip;
        return this;
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
