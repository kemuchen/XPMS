package cn.xpms.member.common.error;

import cn.xpms.system.framework.beans.error.BaseErrorCode;

/**
 * @ClassName CouponErrorCode
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/2 13:42
 * @Version 1.0
 */
public enum CouponErrorCode implements BaseErrorCode {

    /** 优惠券不存在 */
    COUPON_ID_NOT_EXISTS("10001", "领取优惠券传入参数错误", "优惠券不存在"),

    /** 优惠券已过有效期 */
    COUPON_GOOVERRED("10002", "优惠券已过有效期", "优惠券已过有效期"),
	
    /** 用户积分查询失败 */
	INTEGRAL_SELECT_EXISTS("11001", "用户积分查询失败", "积分查询异常"),
	
	/** 用户积分查询无数据 */
	INTEGRAL_SELECT_NULL("11002", "用户积分查询无数据", "积分查询不存在数据");

    CouponErrorCode(String error_code, String error_desc, String tip) {
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
