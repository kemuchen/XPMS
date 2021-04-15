package cn.xpms.system.system.cache.redis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @ClassName RedisProperties
 * @Desc 使用ConfigurationProperties注解读取yml文件中的字段值，并使用Component注入到spring容器中
 * @Author 柯雷
 * @Date 2020/11/2 11:25
 * @Version 1.0
 */
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * @Description: 选择redis作为默认缓存工具
     * @Params: [redisConnectionFactory]
     * @return: org.springframework.cache.CacheManager
     * @Author: 柯雷
     * @Date: 2020/11/3 10:58
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    /**
     * @Description: redisTemplate相关配置
     * @Params: [factory]
     * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/3 10:59
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
        // 值采用json序列化
        template.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
        return template;
    }

    /** 
     * @Description: 对hash类型数据操作
     * @Params: [redisTemplate]
     * @return: org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/3 11:00
     */ 
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * @Description: 对redis字符串类型数据操作
     * @Params: [redisTemplate]
     * @return: org.springframework.data.redis.core.ValueOperations<java.lang.String,java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/3 11:01
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * @Description: 对链表类型数据操作
     * @Params: [redisTemplate]
     * @return: ListOperations<String,Object>
     * @Author: 柯雷
     * @Date: 2020/11/3 11:01
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * @Description: 对无序集合类型数据操作
     * @Params: [redisTemplate]
     * @return: org.springframework.data.redis.core.SetOperations<java.lang.String,java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/3 11:02
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /** 
     * @Description: 对有序集合类型数据操作
     * @Params: [redisTemplate]
     * @return: org.springframework.data.redis.core.ZSetOperations<java.lang.String,java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/11/3 11:02
     */ 
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
