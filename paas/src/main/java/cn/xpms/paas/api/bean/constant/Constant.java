package cn.xpms.paas.api.bean.constant;

/**
 * @ClassName PaasApiConstant
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:10
 * @Version 1.0
 */
public interface Constant {

    /** paas平台系统配置redis缓存key */
    String PAAS_CONFIG_REDIS_KEY = "PAAS_CONFIG_REDIS_KEY";

    /** paas平台接口配置redis缓存key */
    String PAAS_API_CONFIG_REDIS_KEY = "PAAS_API_CONFIG_REDIS_KEY";

    /** paas平台字典项redis缓存key */
    String PAAS_DICT_REDIS_KEY = "PAAS_DICT_REDIS_KEY";

    /** 涂鸦接口平台地址参数名 */
    String APP_URL = "APP_URL";

    /** 涂鸦接口平台ACCESS_ID参数名 */
    String ACCESS_ID = "ACCESS_ID";

    /** 涂鸦接口平台ACCESS_KEY参数名 */
    String ACCESS_KEY = "ACCESS_KEY";

    /** 涂鸦接口平台SECRET_METHOD参数名 */
    String SECRET_METHOD = "SECRET_METHOD";

    /** 涂鸦接口平台GRANT_TYPE参数名 */
    String GRANT_TYPE = "GRANT_TYPE";

    /** api调用成功标识 */
    String API_CALL_RESULT_SUCCESS = "true";

    /** api调用失败标识 */
    String API_CALL_RESULT_FAIL = "false";

    /** api请求体类型 */
    String API_CONTENT_TYPE = "application/json";

    /** 默认时区id */
    String DEFAULT_TIME_ZONE_ID = "Asia/Shanghai";
}
