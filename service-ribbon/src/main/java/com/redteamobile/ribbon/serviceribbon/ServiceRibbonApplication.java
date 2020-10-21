package com.redteamobile.ribbon.serviceribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableEurekaClient 只支持Eureka注册中心，@EnableDiscoveryClient 支持Eureka、Zookeeper、Consul 这三个注册中心。
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EnableRetry
@EnableHystrix
/**
 *
 * ribbon这种 rest调用 当被调用的服务出现问题 是不包含熔断降级的操作
 *
 * Hystrix短路器的核心功能:
 * 降级: 当后台微服务不可用或访问超时时,则转向执行降级代码,或返回错误信息,或返回缓存数据;
 * 熔断: 默认配置下,后台微服务10秒内收到的请求达到20个,并且有一半的请求(50%)出现请求失败降级的情况,则Hystrix打开断路器(断路器默认关闭closed),
 * 表示后台微服务不可用,让所有请求执行降级代码;当断路器打开5秒后转为半开闭状态,该状态表示当有请
 * 求到达时,会尝试向后台微服务转发。如果请求成功,则关闭短路器，表示所有的请求都可请求到达后台
 * 微服务；若仍请求失败，则短路器仍保持打开状态。
 */
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    /**
     * Netflix Ribbon 负载均衡工具
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate(customHttpRequestFactory());
    }


    @Bean
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        //注意良好的时间管控
        httpComponentsClientHttpRequestFactory.setReadTimeout(10);
        return httpComponentsClientHttpRequestFactory;
    }

    /**
     * 测试轮训
     * @return
     */
    @Bean
    public IRule myRule()
    {
        //return new RoundRobinRule();
        return new RandomRule();
    }

}
