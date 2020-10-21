package com.redteamobile.feign;

/**
 * @ClassName ServiceHiHystrics
 * @Description TODO
 * @Author 郑迪文
 * @Date 2020/10/19 1:04 下午
 */

public class ServiceHiHystrics implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        System.out.println("sayHiFromClientOne 异常 " + name);
        return "sayHiFromClientOne 异常";
    }
}
