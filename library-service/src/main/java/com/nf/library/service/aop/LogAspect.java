package com.nf.library.service.aop;

import com.nf.library.dao.LogDao;
import com.nf.library.entity.Log;
import com.nf.library.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 日志监控
 * @author Sam
 */
@Component
@Aspect
@Slf4j
@Order(Integer.MAX_VALUE)
public class LogAspect {

    @Autowired
    private LogDao logDao;

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

    @Pointcut("execution(* com.nf.library.service.impl..*.*Delete(..))")
    public void delete(){

    }
    @Around(value = "delete()")
    public Object getDeleteAround(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            Long start = System.currentTimeMillis();
            result = joinPoint.proceed(joinPoint.getArgs());
            Long end = System.currentTimeMillis();
            log.info("查询时间："+(start-end));
            Log log = Log.builder()
                    .logcontent(getUserInfo().getRealName()+":执行"+joinPoint.getSignature().getName()+"方法删除数据")
                    .operatetype("删除")
                    .userid(getUserInfo().getUserId())
                    .realName(getUserInfo().getRealName())
                    .operatetime(new Date())
                    .totalTime((end-start))
                    .args(getObject(joinPoint.getArgs())).build();
            logDao.insert(log);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        return result;
    }
    private String getObject(Object[] obj){
        String objs = "";
        if(obj.length!=0) {
            for (Object o : obj) {
                objs += o + " , ";
            }
            objs = objs.substring(0,objs.lastIndexOf(","));
        }
        return objs;
    }
    @Pointcut("execution(* com.nf.library.service.impl..*.*Insert(..))")
    public void insert(){

    }
    @Around(value = "insert()")
    public Object getInsertAround(ProceedingJoinPoint joinPoint){

        Object result = null;
        try {
            Long start = System.currentTimeMillis();
            result = joinPoint.proceed(joinPoint.getArgs());
            Long end = System.currentTimeMillis();
            log.info("查询时间："+(start-end));
            Log log = Log.builder()
                    .logcontent(getUserInfo().getRealName()+":执行"+joinPoint.getSignature().getName()+"方法添加数据")
                    .operatetype("添加")
                    .userid(getUserInfo().getUserId())
                    .realName(getUserInfo().getRealName())
                    .operatetime(new Date())
                    .totalTime((end-start)).build();
            logDao.insert(log);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        return result;
    }


    @Pointcut("execution(* com.nf.library.service.impl..*.*Update(..))")
    public void update(){

    }
    @Around(value = "update()")
    public Object getUpdateAround(ProceedingJoinPoint joinPoint){

        Object result = null;
        try {
            Long start = System.currentTimeMillis();
            result = joinPoint.proceed(joinPoint.getArgs());
            Long end = System.currentTimeMillis();
            log.info("查询时间："+(start-end));
            Log log = Log.builder()
                    .logcontent(getUserInfo().getRealName()+":执行"+joinPoint.getSignature().getName()+"方法修改数据")
                    .operatetype("修改")
                    .userid(getUserInfo().getUserId())
                    .realName(getUserInfo().getRealName())
                    .operatetime(new Date())
                    .totalTime((end-start)).build();
            logDao.insert(log);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        return result;
    }

    private UserInfo getUserInfo(){
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo;
    }
}
