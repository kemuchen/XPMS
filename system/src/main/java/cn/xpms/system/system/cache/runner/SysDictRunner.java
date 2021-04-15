package cn.xpms.system.system.cache.runner;

import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.system.cache.base.CacheBaseType;
import cn.xpms.system.system.cache.base.CacheBaseUtil;
import cn.xpms.system.system.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysDictRunner
 * @Desc 系统字典项
 * @Author 柯雷
 * @Date 2020/11/18 10:47
 * @Version 1.0
 */
//@Component
//@Order
public class SysDictRunner implements ApplicationRunner {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(SysDictRunner.class);

    /** 查询系统字典项 */
    SysDictService sysDictService = BeanFactory.getBean(SysDictService.class);

    /**
     * @Description: 加载字典项
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/18 10:49
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.error("【SysDictRunner.run】======开始加载系统字典项缓存=======");
        CacheBaseType cacheBaseType = new CacheBaseType();
        cacheBaseType.setRedis_key(SystemConstants.SYS_DICT_REDIS_KEY)
                .setCacheBaseInterface(sysDictService);
        CacheBaseUtil.flush(cacheBaseType);
    }
}
