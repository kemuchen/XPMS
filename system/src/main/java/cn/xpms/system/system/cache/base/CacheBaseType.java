package cn.xpms.system.system.cache.base;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheBaseType
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/18 11:15
 * @Version 1.0
 */
public class CacheBaseType {

    /** redis缓存key */
    private String redis_key;

    /** 缓存描述 */
    private String cache_desc;

    /** 缓存数据查询接口 */
    private CacheBaseInterface cacheBaseInterface;

    /** 有效时长 */
    private long expire_time;

    /** 有效时长类型 */
    private TimeUnit timeUnit;

    public String getRedis_key() {
        return redis_key;
    }

    public CacheBaseType setRedis_key(String redis_key) {
        this.redis_key = redis_key;
        return this;
    }

    public String getCache_desc() {
        return cache_desc;
    }

    public CacheBaseType setCache_desc(String cache_desc) {
        this.cache_desc = cache_desc;
        return this;
    }

    public CacheBaseInterface getCacheBaseInterface() {
        return cacheBaseInterface;
    }

    public CacheBaseType setCacheBaseInterface(CacheBaseInterface cacheBaseInterface) {
        this.cacheBaseInterface = cacheBaseInterface;
        return this;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public CacheBaseType setExpire_time(long expire_time) {
        this.expire_time = expire_time;
        return this;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public CacheBaseType setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }
}