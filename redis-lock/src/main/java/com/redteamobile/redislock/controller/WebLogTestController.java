package com.redteamobile.redislock.controller;


import com.redteamobile.redislock.annotation.DistributeLock;
import com.redteamobile.redislock.common.base.BaseRequest;
import com.redteamobile.redislock.common.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
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



    @PostMapping("/post-test")
    @ApiOperation("接口日志POST请求测试")
    @DistributeLock(key = "post_test_#{baseRequest.channel}", timeout = 10)
    public BaseResponse postTest(@RequestBody @Valid BaseRequest baseRequest, BindingResult bindingResult) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BaseResponse.addResult();
    }

}
