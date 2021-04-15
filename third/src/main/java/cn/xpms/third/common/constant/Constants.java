package cn.xpms.third.common.constant;

/**
 * @ClassName Constants
 * @Desc
 * @Author ycj
 * @Date 2020-11-12 9:54
 * @Version 1.0
 */
public class Constants {

    /** 公安网字典项redis缓存key */
    public static final String PUBLIC_SECURITY_DICT_REDIS_KEY = "PUBLIC_SECURITY_DICT_REDIS_KEY";

    /** 公安网系统配置redis缓存key */
    public static final String PUBLIC_SECURITY_SYSCONFIG_REDIS_KEY = "PUBLIC_SECURITY_SYSCONFIG_REDIS_KEY";

    /** 公安接口上传类型-入住 */
    public static final String PUBLIC_SECURITY_UPLOAD_TYPE_CHECKIN = "checkin";

    /** 公安网上传类型-退房 */
    public static final String PUBLIC_SECURITY_UPLOAD_TYPE_CHECKOUT = "checkout";

    /** 公安网接口默认上传次数 */
    public static final Integer PUBLIC_SECURITY_UPLOAD_DEFAULT_TIMES = 3;

    /** 公安网接口地址 */
    public static final String PUBLIC_SECURITY_URL = "COMMON_SECURITY_URL";

    /** 公安网接口命名空间 */
    public static final String PUBLIC_SECURITY_NAMESPACE = "COMMON_SECURITY_NAMESPACE";

    /** 公安网接口配置SERVICE */
    public static final String PUBLIC_SECURITY_LOCAL_SERVICE = "HotelServiceService";

    /** 公安网接口配置PORT */
    public static final String PUBLIC_SECURITY_LOCAL_PORT = "HotelServicePort";

    /** 公安网国内旅客登记方法 */
    public static final String PUBLIC_SECURITY_CHECKIN_LOCAL_PART = "gnlkAdd";

    /** 公安网国内旅客退房方法 */
    public static final String PUBLIC_SECURITY_CHECKOUT_LOCAL_PART = "checkOut";

    /** 公安网接口方法前缀 */
    public static final String PUBLIC_SECURITY_PREFIX = "tns";

    /** 默认民族 */
    public static final String PUBLIC_SECURITY_DEFAULT_NATION = "汉";

    /** 默认证件类型 */
    public static final String PUBLIC_SECURITY_DEFAULT_CARD_TYPE = "身份证";

    /** 公安网人脸比对默认结果 */
    public static final String PUBLIC_SECURITY_VERIFY_RESULT = "Y";

    /** 公安网人脸比对默认分时 */
    public static final String PUBLIC_SECURITY_VERIFY_SCORE = "99";

    /** 公安网登记成功 */
    public static final String PUBLIC_SECURITY_CHECKIN_SUCCESS = "登记成功";

    public static final String PUBLIC_SECURITY_CHECKOUT_SUCCESS = "退房成功";

    /** 上传成功状态 */
    public static final String PUBLIC_SECURITY_UPLOAD_SUCCESS_RESULT = "Y";

    /** 上传失败状态 */
    public static final String PUBLIC_SECURITY_UPLOAD_FAIL_RESULT = "N";
}
