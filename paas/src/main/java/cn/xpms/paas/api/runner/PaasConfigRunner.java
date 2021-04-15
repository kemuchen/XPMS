package cn.xpms.paas.api.runner;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.service.common.inter.config.PaasConfigServiceInterface;
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
 * @ClassName PassConfigRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/23 10:24
 * @Version 1.0
 */
@Component
@Order(2)
public class PaasConfigRunner implements ApplicationRunner {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PaasConfigRunner.class);

    /** paas平台系统配置service */
    @Autowired
    PaasConfigServiceInterface paasConfigServiceInterface;

    /**
     * @Description: 加载paas平台系统缓存
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/23 10:26
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.error("【PaasConfigRunner.run】======加载paas平台系统缓存=======");
        CacheBaseType cacheBaseType = new CacheBaseType();
        cacheBaseType.setRedis_key(Constant.PAAS_CONFIG_REDIS_KEY)
                .setCacheBaseInterface(paasConfigServiceInterface);
        CacheBaseUtil.flush(cacheBaseType);
    }
}
