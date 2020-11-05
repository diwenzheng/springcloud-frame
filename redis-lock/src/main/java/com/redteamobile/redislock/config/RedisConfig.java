package com.redteamobile.redislock.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig  {

    @Autowired
    RedisProperties redisProperties;

    @Bean
    @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
    public JedisConnectionFactory jedisConnectionFactory() {
        System.out.println("JedisConnectionFactory redisProperties:" + redisProperties);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                redisProperties.getSingle().getAddress().split(":")[0],
                NumberUtils.toInt(redisProperties.getSingle().getAddress().split(":")[1], 6379));
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        }
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        Objects.requireNonNull(jedisConnectionFactory.getPoolConfig()).setMaxTotal(redisProperties.getPool().getMaxActive());
        jedisConnectionFactory.getPoolConfig().setMaxIdle(redisProperties.getPool().getMaxIdle());
        jedisConnectionFactory.getPoolConfig().setMinIdle(redisProperties.getPool().getMinIdle());
        jedisConnectionFactory.getPoolConfig().setBlockWhenExhausted(true);
        jedisConnectionFactory.getPoolConfig().setMaxWaitMillis(redisProperties.getPool().getMaxWait());
        jedisConnectionFactory.getPoolConfig().setTestOnBorrow(redisProperties.getPool().getTestOnBorrow());
        jedisConnectionFactory.getPoolConfig().setTestOnReturn(redisProperties.getPool().getTestOnReturn());
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
    public JedisConnectionFactory sentinelJedisConnectionFactory() {
        System.out.println("JedisConnectionFactory redisProperties:" + redisProperties);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getPool().getMaxActive());
        jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setTestOnBorrow(redisProperties.getPool().getTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(redisProperties.getPool().getTestOnReturn());

        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        String[] nodeArr = redisProperties.getSentinel().getNodes().split(",");
        //配置matser的名称
        redisSentinelConfiguration.master(redisProperties.getSentinel().getMaster());
        //配置redis的哨兵sentinel
        Set<RedisNode> redisNodeSet = new HashSet<>();
        Stream.of(nodeArr).forEach(x -> redisNodeSet.add(new RedisNode(x.split(":")[0], Integer.parseInt(x.split(":")[1]))));
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            redisSentinelConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        redisSentinelConfiguration.setDatabase(redisProperties.getDatabase());
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder().connectTimeout(Duration.ofMillis(redisProperties.getPool().getConnTimeout())).readTimeout(Duration.ofMillis(redisProperties.getPool().getSoTimeout()));
        //指定jedisPoolConifig来修改默认的连接池构造器
        jpcb.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration, jedisClientConfiguration);

        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            jedisConnectionFactory.setPassword(redisProperties.getPassword());
        }
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    /**
     * 配置 Redis Cluster 信息
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
    public JedisConnectionFactory clusterJedisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        List<RedisNode> nodeList = new ArrayList<>();
        String[] cNodes = redisProperties.getCluster().getNodes().split(",");
        //分割出集群节点
        for(String node : cNodes) {
            String[] hp = node.split(":");
            nodeList.add(new RedisNode(hp[0], Integer.parseInt(hp[1])));
        }
        redisClusterConfiguration.setClusterNodes(nodeList);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getPool().getMaxActive());
        jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
        jedisPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setTestOnBorrow(redisProperties.getPool().getTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(redisProperties.getPool().getTestOnReturn());
        return new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        // hash的value序列化方式采用jackson
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.setValueSerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);

        template.afterPropertiesSet();
        Properties info = template.getRequiredConnectionFactory().getConnection().info("all");
        return template;
    }
}
