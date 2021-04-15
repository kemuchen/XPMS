package cn.xpms.paas.api.runner;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.service.common.inter.config.PaasApiConfigServiceInterface;
import cn.xpms.system.system.cache.base.CacheBaseType;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName PaasApiConfigRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:25
 * @Version 1.0
 */
@Component
@Order(3)
public class PaasApiConfigRunner implements ApplicationRunner {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PaasApiConfigRunner.class);

    /** paas平台接口配置service */
    @Autowired
    PaasApiConfigServiceInterface paasApiConfigServiceInterface;

    /**
     * @Description: 加载paas平台接口配置
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/23 11:48
     */
    @Override
    public void run(ApplicationArguments args) {
        System.err.println("【PaasConfigRunner.run】======加载paas平台接口配置=======");
        logger.error("【PaasConfigRunner.run】======加载paas平台接口配置=======");
        CacheBaseType cacheBaseType = new CacheBaseType();
        cacheBaseType.setRedis_key(Constant.PAAS_API_CONFIG_REDIS_KEY)
                .setCacheBaseInterface(paasApiConfigServiceInterface);
        CacheBaseUtil.flush(cacheBaseType);
    }
}
