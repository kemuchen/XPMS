package cn.xpms.system.system.cache.runner;

import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.system.cache.base.CacheBaseType;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import cn.xpms.system.system.service.SubSysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName SubSysConfigRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/12 11:58
 * @Version 1.0
 */
//@Component
//@Order
public class SubSysConfigRunner implements ApplicationRunner {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(SubSysConfigRunner.class);

    /** 子系统配置缓存 */
    SubSysConfigService subSysConfigService = BeanFactory.getBean(SubSysConfigService.class);

    /**
     * @Description: 加载系统缓存
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/12 11:59
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.error("【SubSysConfigRunner.run】======开始加载系统配置缓存=======");
        CacheBaseType cacheBaseType = new CacheBaseType();
        cacheBaseType.setRedis_key(SystemConstants.SYS_SUBSYS_CONFIG_REDIS_KEY)
                .setCacheBaseInterface(subSysConfigService);
        CacheBaseUtil.flush(cacheBaseType);
    }
}
