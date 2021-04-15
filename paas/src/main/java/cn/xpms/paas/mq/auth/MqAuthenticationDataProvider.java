package cn.xpms.paas.mq.auth;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.pulsar.client.api.AuthenticationDataProvider;

/**
 * @ClassName MqAuthenticationDataProvider
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/17 9:11
 * @Version 1.0
 */
public class MqAuthenticationDataProvider implements AuthenticationDataProvider {

    /** 命令数据对象 */
    private String commandData;

    /**
     * @Description: 认证
     * @Params: [accessId, accessKey]
     * @return:
     * @Author: 柯雷
     * @Date: 2020/11/17 10:29
     */
    public MqAuthenticationDataProvider(String accessId, String accessKey) {
        this.commandData = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", accessId,
                DigestUtils.md5Hex(accessId + DigestUtils.md5Hex(accessKey)).substring(8, 24));
    }

    @Override
    public String getCommandData() {
        return commandData;
    }

    @Override
    public boolean hasDataForHttp() {
        return false;
    }

    @Override
    public boolean hasDataFromCommand() {
        return true;
    }
}