package cn.xpms.third.common.cache;

import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.third.common.constant.Constants;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityDict;
import cn.xpms.third.security.dao.generator.entity.PublicSecurityDictExample;
import cn.xpms.third.security.dao.generator.repository.PublicSecurityDictMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PublicSecurityDictUtil
 * @Desc
 * @Author ycj
 * @Date 2020-11-12 9:50
 * @Version 1.0
 */
public class PublicSecurityDictUtil {

    /**
     * 日志打印对象
     */
    static Logger logger = LoggerFactory.getLogger(PublicSecurityDictUtil.class);

    /**
     * 系统字典CURD操作
     */
    static PublicSecurityDictMapper publicSecurityDictMapper = BeanFactory.getBean(PublicSecurityDictMapper.class);

    /**
     * redis缓存
     */
    static RedisTemplate redisTemplate = (RedisTemplate) BeanFactory.getBean("redisTemplate");

    /**
     * 获取字典项
     *
     * @param code
     * @return
     */
    public static List<PublicSecurityDict> getPublicSecurityDict(String code) {
        logger.info("【PublicSecurityDictUtil.getPublicSecurityDict】获取公安网接口字典项字典项");
        ValueOperations<String, List> operations = redisTemplate.opsForValue();

        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(Constants.PUBLIC_SECURITY_DICT_REDIS_KEY);
        if (hasKey) {
            List<PublicSecurityDict> securityDicts = operations.get(Constants.PUBLIC_SECURITY_DICT_REDIS_KEY);
            List<PublicSecurityDict> dicts = new ArrayList<PublicSecurityDict>();
            for (PublicSecurityDict dict : securityDicts) {
                if (dict.getTypeCode().equals(code)) {
                    dicts.add(dict);
                }
            }
            return dicts;
        } else {
            return null;
        }
    }

    /**
     * 获取字典项
     *
     * @param code
     * @param value
     * @return
     */
    public static PublicSecurityDict getPublicSecurityDict(String code, String value) {
        logger.info("【PublicSecurityDictUtil.getPublicSecurityDict】获取公安网接口字典项字典项");
        List<PublicSecurityDict> securityDicts = getPublicSecurityDict(code);
        for (PublicSecurityDict dcit : securityDicts) {
            if (dcit.getTypeValue().equals(value)) {
                return dcit;
            }
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
        logger.info("【PublicSecurityDictUtil.flush】加载静态字典项缓存");

        // 获取有效的字典项
        PublicSecurityDictExample dictExample = new PublicSecurityDictExample();
        dictExample.createCriteria().andValidEqualTo(SystemConstants.INT_YES);

        ValueOperations<String, List> operations = redisTemplate.opsForValue();
        // 查询并存入缓存
        operations.set(Constants.PUBLIC_SECURITY_DICT_REDIS_KEY,
                publicSecurityDictMapper.selectByExample(dictExample));
    }
}
