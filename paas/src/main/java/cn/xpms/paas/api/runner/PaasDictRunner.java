package cn.xpms.paas.api.runner;

import cn.xpms.paas.api.bean.constant.Constant;
import cn.xpms.paas.api.service.common.inter.config.PaasConfigServiceInterface;
import cn.xpms.paas.api.service.common.inter.config.PaasDictServiceInterface;
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
 * @ClassName PaasDictRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020/12/3 11:15
 * @Version 1.0
 */
@Component
@Order(1)
public class PaasDictRunner implements ApplicationRunner {
    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PaasDictRunner.class);

    /** paas平台字典项service */
    @Autowired
    PaasDictServiceInterface paasDictServiceInterface;

    /**
     * @Description: 加载paas平台字典项缓存
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/23 10:26
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.error("【PaasDictRunner.run】======加载paas平台字典项缓存=======");
        CacheBaseType cacheBaseType = new CacheBaseType();
        cacheBaseType.setRedis_key(Constant.PAAS_DICT_REDIS_KEY)
                .setCacheBaseInterface(paasDictServiceInterface);
        CacheBaseUtil.flush(cacheBaseType);
    }
}
