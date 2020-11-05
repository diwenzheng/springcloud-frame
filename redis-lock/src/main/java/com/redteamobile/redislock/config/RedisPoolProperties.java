package com.redteamobile.redislock.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RedisPoolProperties {

    private int maxIdle;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int connTimeout;

    private int soTimeout;

    /**
     * 池大小
     */
    private int size;

    private Boolean testOnBorrow = false;

    private Boolean testOnReturn = false;

}