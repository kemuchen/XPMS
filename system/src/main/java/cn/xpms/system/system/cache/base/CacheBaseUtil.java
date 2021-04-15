package cn.xpms.system.system.cache.base;

import cn.xpms.system.framework.beans.error.AppException;
import cn.xpms.system.framework.beans.error.SysErrorCode;
import cn.xpms.system.framework.beans.pojo.BeanFactory;
import cn.xpms.system.framework.constant.SystemConstants;
import cn.xpms.system.framework.util.sys.SysUtil;
import cn.xpms.system.system.dao.generator.entity.SysDictExample;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheBaseUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 11:15
 * @Version 1.0
 */
public class CacheBaseUtil {

    /**
     * 日志打印对象
     */
    static Logger logger = LoggerFactory.getLogger(CacheBaseUtil.class);

    /** redis缓存 */
    static RedisTemplate redisTemplate = (RedisTemplate) BeanFactory.getBean("redisTemplate");

    /**
     * @Description: 查询缓存数据
     * @Params: [redis_key]
     * @return: java.util.List<java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/18 11:30
     */
    public static <T> T getCacheData(String redis_key) {
        logger.info("【CacheBaseUtil.getCacheData】查询缓存数据");
        ValueOperations<String, List> operations = redisTemplate.opsForValue();

        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(redis_key);
        if (hasKey) {
            return (T) operations.get(redis_key);
        } else {
            return null;
        }
    }

    /**
     * @Description: 刷新缓存
     * @Params: []
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-06-28 10:40
     */
    public static void flush(CacheBaseType cacheBaseType) {
        logger.info("【CacheBaseUtil.flush】缓存基础类：刷新缓存数据");
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Object cacheData = cacheBaseType.getCacheBaseInterface().getCacheData();
        if (!SysUtil.isEmpty(cacheBaseType.getExpire_time()) &&
                !SysUtil.isEmpty(cacheBaseType.getTimeUnit())) {
            // 查询并存入缓存
            operations.set(cacheBaseType.getRedis_key(),
                    cacheData, cacheBaseType.getExpire_time(), cacheBaseType.getTimeUnit());
        } else {
            operations.set(cacheBaseType.getRedis_key(), cacheData);
        }
    }

    /**
     * @Description:
     * @Params: [key, data]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/18 16:48
     */
    public static <T> void set(String key, T data) throws AppException {
        try {
            ValueOperations<String, T> operations = redisTemplate.opsForValue();
            operations.set(key, data);
        } catch (Exception e) {
            logger.error("【CacheBaseUtil.set】存入缓存失败");
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 设置值
     * @Params: [key, data, t, timeUnit]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/18 16:56
     */
    public static <T> void set(String key, T data, long t, TimeUnit timeUnit) throws AppException {
        try {
            ValueOperations<String, T> operations = redisTemplate.opsForValue();
            operations.set(key, data, t, timeUnit);
        } catch (Exception e) {
            logger.error("【CacheBaseUtil.set】存入缓存失败");
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 删除缓存
     * @Params: [key]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/24 11:26
     */
    public static void delete(String key) throws AppException {
        try {
            redisTemplate.opsForValue().getOperations().delete(key);
        } catch (Exception e) {
            logger.error("【CacheBaseUtil.delete】删除缓存失败");
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}