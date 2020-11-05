package com.redteamobile.redislock.service;

import com.redteamobile.redislock.config.RedisKeys;
import com.redteamobile.redislock.po.Author;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author zhengdiwen
 */
public interface AuthorService {

    /**
     * condition = "#id != 2"
     * @param namespace
     * @return
     */
    @Cacheable(value="user-key"+ RedisKeys.REDIS_EXPIRE_TIME_KEY +"="+ 1200,key = "#namespace")
    public Author getAuthor(String namespace);
}
