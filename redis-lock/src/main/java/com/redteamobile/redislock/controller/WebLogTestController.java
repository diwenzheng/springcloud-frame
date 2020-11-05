package com.redteamobile.redislock.controller;


import com.redteamobile.redislock.annotation.DistributeLock;
import com.redteamobile.redislock.common.base.BaseRequest;
import com.redteamobile.redislock.common.base.BaseResponse;
import com.redteamobile.redislock.po.Author;
import com.redteamobile.redislock.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Web日志测试
 * @author ganchaoyang
 */
@RestController
@RequestMapping("/weblog")
@Api(tags = "Web日志测试相关接口")
public class WebLogTestController {


    /**
     * jmeter 1s 并发测试通过 使用针对controller层面的加锁
     * @param baseRequest
     * @param bindingResult
     * @return
     */
    @PostMapping("/post-test")
    @ApiOperation("接口日志POST请求测试")
    @DistributeLock(key = "post_test_#{baseRequest.channel}", timeout = 10)
    public BaseResponse postTest(@RequestBody @Valid BaseRequest baseRequest, BindingResult bindingResult) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BaseResponse.addResult();
    }

    @Autowired
    private AuthorService authorService;

    /**
     * @param namespace
     * @return
     */
    @GetMapping("/post-cache")
    @ApiOperation("测试redis缓存")
    public Author postCache(String namespace) {
        return authorService.getAuthor(namespace);
    }
}
