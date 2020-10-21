package com.redteamobile.ribbon.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * RetryPolicy提供了如下策略实现：
 *
 * NeverRetryPolicy：只允许调用RetryCallback一次，不允许重试；
 * AlwaysRetryPolicy：允许无限重试，直到成功，此方式逻辑不当会导致死循环；
 * SimpleRetryPolicy：固定次数重试策略，默认重试最大次数为3次，RetryTemplate默认使用的策略；
 * TimeoutRetryPolicy：超时时间重试策略，默认超时时间为1秒，在指定的超时时间内允许重试；
 * CircuitBreakerRetryPolicy：有熔断功能的重试策略，需设置3个参数openTimeout、resetTimeout和delegate，稍后详细介绍该策略；
 * CompositeRetryPolicy：组合重试策略，有两种组合方式，乐观组合重试策略是指只要有一个策略允许重试即可以，悲观组合重试策略是指只要有一个策略不允许重试即可以，但不管哪种组合方式，组合中的每一个策略都会执行。
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * cloud调用方式 http://大写name/?url
     * @param name
     * @return
     */
    @Retryable(value= {RemoteAccessException.class,NullPointerException.class},
            maxAttempts = 3,    //重试次数
            //重试策略，通过@Backoff注解加入相关策略：delay=4000重试间隔时间(重试延迟时间)为4秒,线程数：multiplier = 1单线程；
            backoff=@Backoff(delay=200,multiplier = 1)
    )


    /**
     *
     * 一个类使用一个线程池
     * threadPoolKey的默认值是groupKey，而groupKey默认值是@HystrixCommand标注的方法所在类名
     *
     * 可以通过在类上加@DefaultProperties( threadPoolKey="xxx" )设置默认的threadPoolKey
     *
     * 可以通过@HystrixCommand( threadPoolKey="xxx" ) 指定当前HystrixCommand实例的threadPoolKey
     *
     * threadPoolKey用于从线程池缓存中获取线程池 和 初始化创建线程池，由于默认以groupKey即类名为threadPoolKey，那么默认所有在一个类中的HystrixCommand共用一个线程池
     *
     * 动态配置线程池 -- 可以通过hystrix.command.HystrixCommandKey.threadPoolKeyOverride=线程池key动
     * 态设置threadPoolKey，对应的HystrixCommand所使用的线程池也会重新创建，还可以继续通过hystrix.
     * threadpool.HystrixThreadPoolKey.coreSize=n和hystrix.threadpool.HystrixThreadPoolKey.
     * maximumSize=n动态设置线程池大小
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        System.out.println("enter");
//        throw new RemoteAccessException("RPC异常");
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }


    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

    /**
     * 如果依照规定的重试策略，重试3次依然失败
     * 启动retry提供的降级策略;
     * recover(RemoteAccessException e)参数即重试策略的条件，参数必须匹配 返回值必须一致
     */
    @Recover
    public String recover(String name,RemoteAccessException e) {
        return "最终处理结果1：\"+e.getMessage()";
    }


}

