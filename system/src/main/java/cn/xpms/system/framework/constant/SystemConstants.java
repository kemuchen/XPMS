package cn.xpms.system.framework.constant;

/**
 * @ClassName SystemConstants
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/2 11:54
 * @Version 1.0
 */
public class SystemConstants {

    /** 是否标志 1-是 */
    public static final Integer INT_YES = 1;

    /** 是否标志 0-否 */
    public static final Integer INT_NO = 0;

    /** 是否标志 1-是 */
    public static String STR_YES = "1";

    /** 是否标志 0-否 */
    public static String STR_NO= "0";

    /** 消息队列默认睡眠时间 */
    public static final Integer QUEUE_DEFAULT_SLEEP_TIME = 30000;

    /** 子系统配置redis缓存key */
    public static final String SYS_SUBSYS_CONFIG_REDIS_KEY = "SYS_SUBSYS_CONFIG";

    /** 系统字典项redis缓存key */
    public static final String SYS_DICT_REDIS_KEY = "SYS_DICT_REDIS_KEY";

    /** 空串 */
    public static final String BLANK_STRING = "";

    /** 默认日期格式 */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** 默认时间戳格式 */
    public static final String DEFAULT_TIMESTAMP_PATTERN = "yyyyMMddHHmmss";

    /** 默认的登录session有效期 */
    public static final Integer DEFAULT_LOGIN_EXPIRE_TIME = 30;

    /** http请求get方法 */
    public static final String GET = "GET";

    /** http请求post方法 */
    public static final String POST = "POST";

    /** http请求put方法 */
    public static final String PUT = "PUT";

    /** http请求delete方法 */
    public static final String DELETE = "DELETE";

    /** 默认字符集-UTF-8 */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /** 默认超时时间 */
    public static final Integer DEFAULT_TIMEOUT = 30000;

    /** 默认的序列化参数 */
    public static final String DEFAULT_SERIAL_VERSION_UID = "serialVersionUID";

    /** 字符串ture */
    public static final String STR_TRUE = "true";

    /** 字符串false */
    public static final String STR_FALSE = "false";

    /** page_size参数名 */
    public static final String PAGE_SIZE_PARAM_NAME = "page_size";

    /** page_no参数名 */
    public static final String PAGE_NO_PARAM_NAME = "page_no";

    /** 默认页面大小 */
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    /** 默认页面数 */
    public static final Integer DEFAULT_PAGE_NO = 1;
}
