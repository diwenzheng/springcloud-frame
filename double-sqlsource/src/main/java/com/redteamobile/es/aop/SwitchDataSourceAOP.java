package com.redteamobile.es.aop;

import com.redteamobile.es.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Lazy(false)
@Order(0) // Order设定AOP执行顺序 使之在数据库事务上先执行  动态数据源事务先执行的
public class SwitchDataSourceAOP {

    // 这里切到你的方法目录
    @Before("execution(* com.redteamobile.es.service.*.*(..))")   //扫包范围是业务逻辑层
    public void process(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();  //通过反射获取到方法名称
        if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
                || methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")) {
            // 读
            DataSourceContextHolder.setDbType("selectDataSource");
        } else {
            // 切换dataSource
            DataSourceContextHolder.setDbType("updateDataSource");
        }
    }
}