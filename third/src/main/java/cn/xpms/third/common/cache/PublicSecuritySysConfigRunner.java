package cn.xpms.third.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName PublicSecuritySysConfigRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/13 15:09
 * @Version 1.0
 */
@Component
@Order
public class PublicSecuritySysConfigRunner implements ApplicationRunner {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PublicSecuritySysConfigRunner.class);

    /**
     * @Description: 加载缓存
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 15:20
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.error("【PublicSecuritySysConfigRunner.run】=======加载公安网接口系统配置========");
        PublicSecuritySysConfigUtil.flush();
    }
}
