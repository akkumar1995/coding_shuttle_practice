package com.avinash.kumar.module10.aspects;

import jakarta.persistence.JoinColumn;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
//    @Before("execution(* com.avinash.kumar.module10.services.impl.*.*(..))")
//    public void beforeOrderPackage(JoinPoint joinPoint){
//        log.info("before order package called from logging aspect, {}",joinPoint);
//    }
    @Before("within(com.avinash.kumar.module10.services.impl.*)")
    public void beforeImplCalls(JoinPoint joinPoint){
        log.info("before service impl called from logging aspect, {}",joinPoint);
    }

    @Pointcut("@annotation(com.avinash.kumar.module10.aspects.MyLoggingAnnotation)")
    public void MyLoggingAnnotationPointCut(){

    }
}
