package cn.xpms.member.common.constant;

/**
 * @ClassName Constants
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/2 11:40
 * @Version 1.0
 */
public class Constants {

    /** 优惠券字典项redis缓存key */
    public static String MEMBER_DICT_REDIS_KEY = "MEMBER_DICT_REDIS_KEY";

    /** 优惠券有效期类型-固定日期区间 */
    public static String COUPON_DICT_DATE_FIX_TIME_RANGE = "DATE_TYPE_FIX_TIME_RANGE";

    /** 优惠券有效期类型-固定市场 */
    public static String COUPON_DICT_DATE_FIX_TERM = "DATE_TYPE_FIX_TERM";

    /** 优惠券状态-正常 */
    public static String COUPON_STATUS_OK = "OK";

    /** 优惠券状态-待审核 */
    public static String COUPON_STATUS_UNVERIFY = "UNVERIFY";

    /** 优惠券状态-待投放 */
    public static String COUPON_STATUS_UNSEND = "UNSEND";

    /** 优惠券状态-停用 */
    public static String COUPON_STATUS_UNUSERD = "UNUSERD";

    /** 优惠券领取记录状态-正常 */
    public static String COUPON_RECORD_STATUS_OK = "OK";

    /** 优惠券领取记录状态-已核销 */
    public static String COUPON_RECORD_STATUS_CONSUMED = "CONSUMED";

    /** 优惠券领取记录状态-已过期 */
    public static String COUPON_RECORD_STATUS_GOOVERED = "GOOVERED";

    /** 优惠券领取记录状态-已删除 */
    public static String COUPON_RECORD_STATUS_DELETED = "DELETED";
}
