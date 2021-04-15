package cn.xpms.paas.mq.config;

import cn.xpms.paas.mq.auth.MqAuthentication;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.DeadLetterPolicy;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.SubscriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName MqConsumer
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/17 9:11
 * @Version 1.0
 */
public class MqConsumer {

    /** 日志打印对象 */
    private static final Logger	logger = LoggerFactory.getLogger(MqConsumer.class);

    /** PASS平台服务器地址 */
    private String serviceUrl;

    /** 开发者账号 */
    private String accessId;

    /** 授权秘钥 */
    private String accessKey;

    /** 运行环境 */
    private MqEnv env = MqEnv.PROD;

    /** 最大活动数量 */
    private int maxRedeliverCount = 3;

    /** 消息监听器 */
    private IMessageListener messageListener;


    public static MqConsumer build() {
        return new MqConsumer();
    }

    public MqConsumer serviceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        return this;
    }

    public MqConsumer accessId(String accessId) {
        this.accessId = accessId;
        return this;
    }

    public MqConsumer accessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public MqConsumer env(MqEnv env) {
        this.env = env;
        return this;
    }

    public MqConsumer maxRedeliverCount(int maxRedeliverCount) {
        this.maxRedeliverCount = maxRedeliverCount;
        return this;
    }

    public MqConsumer messageListener(IMessageListener messageListener) {
        this.messageListener = messageListener;
        return this;
    }

    /**
     * Start consumer
     *
     * @throws Exception
     */
    public void start() throws Exception {
        if (serviceUrl == null || serviceUrl.trim().length() == 0) {
            throw new IllegalStateException("serviceUrl must be initialized");
        }
        if (accessId == null || accessId.trim().length() == 0) {
            throw new IllegalStateException("accessId must be initialized");
        }
        if (accessKey == null || accessKey.trim().length() == 0) {
            throw new IllegalStateException("accessKey must be initialized");
        }
        if (messageListener == null) {
            throw new IllegalStateException("messageListener must be initialized");
        }
        PulsarClient client = PulsarClient.builder().serviceUrl(serviceUrl).allowTlsInsecureConnection(true)
                .authentication(new MqAuthentication(accessId, accessKey)).build();
        Consumer consumer = client.newConsumer().topic(String.format("%s/out/%s", accessId, env.getValue()))
                .subscriptionName(String.format("%s-sub", accessId)).subscriptionType(SubscriptionType.Failover)
                .deadLetterPolicy(DeadLetterPolicy.builder().maxRedeliverCount(maxRedeliverCount).build()).subscribe();
        do {
            try {
                Message message = consumer.receive();
                Long s = System.currentTimeMillis();
                messageListener.onMessageArrived(message);
                if (MqConfigs.DEBUG) {
                    logger.info("business processing cost={}", System.currentTimeMillis() - s);
                }
                consumer.acknowledge(message);
            } catch (Throwable t) {
                logger.error("error:", t);
            }
        } while (true);
    }

    public interface IMessageListener {
        void onMessageArrived(Message message) throws Exception;
    }
}