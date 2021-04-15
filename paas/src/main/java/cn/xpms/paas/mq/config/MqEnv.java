package cn.xpms.paas.mq.config;

/**
 * @ClassName MqEnv
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/17 9:12
 * @Version 1.0
 */
public enum MqEnv {

    PROD("prod", "event", "online environment"),
    TEST("test", "event-test", "test environment");

    private String	key;

    private String	value;

    private String	description;

    MqEnv(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
