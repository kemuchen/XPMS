package cn.xpms.paas.mq.config;

/**
 * @ClassName MqConfigs
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/17 9:11
 * @Version 1.0
 */
public class MqConfigs {

    /** 中国区地址 */
    public static final String CN_SERVER_URL = "pulsar+ssl://mqe.tuyacn.com:7285/";

    /** 美洲区地址 */
    public static final String US_SERVER_URL = "pulsar+ssl://mqe.tuyaus.com:7285/";

    /** 欧洲区地址 */
    public static final String EU_SERVER_URL = "pulsar+ssl://mqe.tuyaeu.com:7285/";

    /** 印度区地址 */
    public static final String IND_SERVER_URL = "pulsar+ssl://mqe.tuyain.com:7285/";

    /** 是否为debug模式 */
    public static final boolean DEBUG = true;
}
