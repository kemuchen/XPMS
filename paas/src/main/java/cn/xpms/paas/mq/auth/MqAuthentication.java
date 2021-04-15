package cn.xpms.paas.mq.auth;

import java.io.IOException;
import java.util.Map;
import org.apache.pulsar.client.api.Authentication;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @ClassName MqAuthentication
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/17 9:10
 * @Version 1.0
 */
public class MqAuthentication implements Authentication {

    /** 开发者账号 */
    private String	accessId;

    /** 授权秘钥 */
    private String	accessKey;

    /**
     * @Description: 构造函数
     * @Params: [accessId, accessKey]
     * @return:
     * @Author: 柯雷
     * @Date: 2020/11/17 10:29
     */
    public MqAuthentication(String accessId, String accessKey) {
        this.accessId = accessId;
        this.accessKey = accessKey;
    }

    /**
     * @Description: 获取验证方法名
     * @Params: []
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/11/17 10:29
     */
    @Override
    public String getAuthMethodName() {
        return "auth1";
    }

    @Override
    public org.apache.pulsar.client.api.AuthenticationDataProvider getAuthData() throws PulsarClientException {
        return new MqAuthenticationDataProvider(this.accessId, this.accessKey);
    }

    @Override
    public void configure(Map<String, String> map) {
    }

    @Override
    public void start() throws PulsarClientException {
    }

    @Override
    public void close() throws IOException {
    }
}
