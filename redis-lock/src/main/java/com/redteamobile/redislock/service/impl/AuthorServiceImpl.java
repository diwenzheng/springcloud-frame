package com.redteamobile.redislock.service.impl;

import com.redteamobile.redislock.mapper.AuthorMapper;
import com.redteamobile.redislock.po.Author;
import com.redteamobile.redislock.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhengdiwen
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;


    @Override
    public Author getAuthor(String namespace) {
        return authorMapper.getAuthorByAppIdAndNameSpaceName("service_provision_01",namespace);
    }
}
