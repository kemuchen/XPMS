package cn.xpms.member.common.runner;

import cn.xpms.member.common.constant.Constants;
import cn.xpms.member.common.service.MemberDictService;
import cn.xpms.system.framework.beans.pojo.BeanFactory;
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
 * @ClassName LoadCacheUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/3 11:38
 * @Version 1.0
 */
@Component
@Order
public class MemberDictRunner implements ApplicationRunner {

    /** 日志打印对象 */
    Logger logger = LoggerFactory.getLogger(MemberDictRunner.class);

    /** 查询会员子系统字典项 */
    @Autowired
    MemberDictService memberDictService;

    /**
     * @Description: 加载缓存
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/3 11:39
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.error("【MemberDictRunner.run】======加载会员子系统字典项=======");
        CacheBaseType cacheBaseType = new CacheBaseType();
        cacheBaseType.setRedis_key(Constants.MEMBER_DICT_REDIS_KEY)
                .setCacheBaseInterface(memberDictService);
        CacheBaseUtil.flush(cacheBaseType);
    }
}
