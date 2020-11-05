package com.redteamobile.redislock.aop;


import com.redteamobile.redislock.annotation.AnnotationResolver;
import com.redteamobile.redislock.annotation.DistributeLock;
import com.redteamobile.redislock.common.RedisLockService;
import com.redteamobile.redislock.common.base.BaseResponse;
import com.redteamobile.redislock.common.enums.ErrorCodeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Aspect
@Component
@Order(99)
public class DistributeLockAspect {



    @Autowired
    private RedisLockService redisLockService;

    @Autowired
    private AnnotationResolver annotationResolver;


    @Pointcut("execution(* com.redteamobile.redislock.controller..*.*(..))")
    public void distribute() {}


    @Around(value = "distribute()&& @annotation(distributeLock)")
    public Object doAround(ProceedingJoinPoint joinPoint, @NotNull DistributeLock distributeLock) throws Exception {
        String key = annotationResolver.resolver(joinPoint, distributeLock.key());
        return redisLockService.lockAspectRun(key, distributeLock.waitTime(), distributeLock.timeout(), () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                return BaseResponse.addError(ErrorCodeEnum.SYSTEM_ERROR, "系统异常");
            }
        });
    }


}
