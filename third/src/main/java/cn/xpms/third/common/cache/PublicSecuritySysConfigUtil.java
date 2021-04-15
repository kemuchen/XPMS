package cn.xpms.third.common.cache;

import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.third.common.constant.Constants;
import cn.xpms.third.security.dao.generator.entity.PublicSecuritySysConfig;
import cn.xpms.third.security.dao.generator.entity.PublicSecuritySysConfigExample;
import cn.xpms.third.security.dao.generator.repository.PublicSecuritySysConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.util.List;

/**
 * @ClassName PublicSecuritySysConfigUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/13 15:09
 * @Version 1.0
 */
public class PublicSecuritySysConfigUtil {

    /**
     * 日志打印对象
     */
    static Logger logger = LoggerFactory.getLogger(PublicSecuritySysConfigUtil.class);

    /**
     * 系统字典CURD操作
     */
    static PublicSecuritySysConfigMapper publicSecuritySysConfigMapper =
            BeanFactory.getBean(PublicSecuritySysConfigMapper.class);

    /**
     * redis缓存
     */
    static RedisTemplate redisTemplate = (RedisTemplate) BeanFactory.getBean("redisTemplate");

    /**
     * @Description: 获取公安网接口系统配置参数
     * @Params: [code]
     * @return: cn.xpms.third.security.dao.generator.entity.PublicSecuritySysConfig
     * @Author: 柯雷
     * @Date: 2020/11/13 15:14
     */
    public static PublicSecuritySysConfig getPublicSecuritySysConfig(String code) {
        logger.info("【PublicSecuritySysConfigUtil.getPublicSecuritySysConfig】获取公安网接口系统配置信息");
        ValueOperations<String, List> operations = redisTemplate.opsForValue();

        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(Constants.PUBLIC_SECURITY_SYSCONFIG_REDIS_KEY);
        if (hasKey) {
            List<PublicSecuritySysConfig> publicSecuritySysConfigs =
                    operations.get(Constants.PUBLIC_SECURITY_SYSCONFIG_REDIS_KEY);
            for (PublicSecuritySysConfig config : publicSecuritySysConfigs) {
                if (config.getCode().equals(code)) {
                    return config;
                }
            }
        }
        return null;
    }

    /**
     * @Description: 获取公安网接口系统配置参数值
     * @Params: [code, value]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/11/13 15:16
     */
    public static String getPublicSecuritySysConfigValue(String code) {
        logger.info("【PublicSecuritySysConfigUtil.getPublicSecuritySysConfigValue】获取公安网接口系统配置参数值");
        PublicSecuritySysConfig config = getPublicSecuritySysConfig(code);
        if (!SysUtil.isEmpty(config)) {
            return config.getValue();
        }
        return null;
    }

    /**
     * @Description: 刷新缓存
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-06-28 10:40
     */
    public static void flush() {
        logger.info("【PublicSecuritySysConfigUtil.flush】加载公安网接口系统配置缓存");

        // 获取有效的系统缓存
        PublicSecuritySysConfigExample configExample = new PublicSecuritySysConfigExample();
        configExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);

        ValueOperations<String, List> operations = redisTemplate.opsForValue();
        // 查询并存入缓存
        operations.set(Constants.PUBLIC_SECURITY_SYSCONFIG_REDIS_KEY,
                publicSecuritySysConfigMapper.selectByExample(configExample));
    }
}
