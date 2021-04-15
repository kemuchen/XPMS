package cn.xpms.third.common.error;

import cn.xpms.system.framework.beans.error.BaseErrorCode;

/**
 * @ClassName PublicSecurityErrorCode
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/13 9:28
 * @Version 1.0
 */
public enum PublicSecurityErrorCode implements BaseErrorCode {

    /** 上传成功 */
    PUBLIC_SECURITY_UPLOAD_SUCCESS("30000", "上传公安网接口", "上传成功"),

    /** 酒店未授权 */
    NO_PUBLIC_SECURITY_CONFIG("30001", "缺少酒店公安网配置项", "酒店未授权"),

    /** 酒店公安网接口关闭 */
    PUBLIC_SECURITY_OFF("30002", "酒店公安网接口关闭", "酒店公安网接口关闭"),

    /** 酒店公安网接口关闭 */
    NO_UPLOAD_INFO("30003", "没有入住上传公安网信息", "无入住信息"),

    /** 公安网接口系统配置异常 */
    UPLOAD_SYSCONFIG_ERROR("30004", "公安网接口系统配置异常", "系统配置异常"),

    /** 公安网入住登记失败 */
    CHECKIN_UPLOAD_FAIL("30005", "公安网入住登记失败", "登记失败"),

    /** 公安网退房登记失败 */
    CHECKOUT_UPLOAD_FAIL("30006", "公安网退房登记失败", "退房失败"),

    /** 查询单号不能为空 */
    NO_QUERY_ORDER_NO("30007", "查询单号不能为空", "查询单号不能为空");

    PublicSecurityErrorCode(String error_code, String error_desc, String tip) {
        this.error_code = error_code;
        this.error_desc = error_desc;
        this.tip = tip;
    }

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

    @Override
    public String getError_code() {
        return null;
    }

    @Override
    public String getError_desc() {
        return null;
    }

    @Override
    public String getTip() {
        return null;
    }
}
