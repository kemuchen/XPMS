package cn.xpms.third.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName PublicSecirutyDictRunner
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/13 8:32
 * @Version 1.0
 */
@Component
@Order
public class PublicSecirutyDictRunner implements ApplicationRunner {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(PublicSecirutyDictRunner.class);

    /**
     * @Description: 加载公安网接口字典项
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/13 15:05
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.error("【PublicSecirutyDictRunner.run】=======加载公安网接口字典项配置========");
        PublicSecurityDictUtil.flush();
    }
}
