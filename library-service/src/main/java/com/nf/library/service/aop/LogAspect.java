package com.nf.library.service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志监控
 * @author Sam
 */
@Component
@Aspect
@Slf4j
@Order(Integer.MAX_VALUE)
public class LogAspect {

    @Pointcut("execution(* com.nf.library.service.impl..*.get*(..))")
    public void getCell(){

    }
    @Around(value = "getCell()")
    public Object getAround(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            Long start = System.currentTimeMillis();
            result = joinPoint.proceed(joinPoint.getArgs());
            Long end = System.currentTimeMillis();
            log.info("查询时间："+(start-end));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        return result;
    }
}
