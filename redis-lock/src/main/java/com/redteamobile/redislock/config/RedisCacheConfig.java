package com.redteamobile.redislock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @ClassName RedisCacheConfig
 * @Description TODO
 *
 * redis缓存
 *  @Cacheable(value = "my-redis-cache2", key = "'book'+#bid")
 *     Book selectByPrimaryKey(Integer bid);
 *  用于数据分离缓存
 *  @CachePut(value=”testcache”,allEntries=true) 这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中，实现缓存与数据库的同步更新。
 *  清除语法
 *  @CacheEvict(value = “my-redis-cache2”,allEntries = true)
 *  void clear();
 *
 * 删除数据库的时候应该清楚缓存
 * 有一个简单的解决办法，在注解参数里面加上beforeInvocation为true，意思是说当执行这个方法之前执行清除缓存的操作，这样不管这个方法执行成功与否，该缓存都将不存在。
 *
 * 当注解参数加上allEntries为true时，意思是说这个清除缓存是清除当前value值空间下的所有缓存数据。
 *
 * @Author 郑迪文
 * @Date 2020/11/5 11:31 上午
 */
@Configuration
@EnableCaching//开启注解式缓存
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisTemplate redisTemplate;

        /**
         * 生成key的策略 根据类名+方法名+所有参数的值生成唯一的一个key
         *
         * @return
         */
        @Bean
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


    @Override
    @Bean
    public CacheManager cacheManager() {

        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        //上面实现的缓存读写
        RedisCacheWriterCustomer cachaWriterCustomer
                = new RedisCacheWriterCustomer(connectionFactory);

        CacheManager cm
                = new RedisCacheManager(cachaWriterCustomer,redisCacheConfiguration());

        return cm;
    }
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())).entryTtl(Duration.ofSeconds(30));

        return configuration;
    }

}
