package com.bcht.axletempmonitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    /** 载入通过配置文件配置的连接工厂 **/
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @SuppressWarnings("rawtypes")
    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    RedisTemplate<String, Object> objRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.cache.annotation.CachingConfigurerSupport#
     * cacheManager()
     */
    @Bean // 必须添加此注解
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager redisCacheManager = RedisCacheManager.create(connectionFactory);
        // 设置缓存过期时间
         //redisCacheManager.setDefaultExpiration(60);//秒
        return redisCacheManager;
    }

    /**
     * 重写缓存的key生成策略,可根据自身业务需要进行自己的配置生成条件
     *
     * @see org.springframework.cache.annotation.CachingConfigurerSupport#
     * keyGenerator()
     */
    @Bean // 必须项
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
