package com.avinash.kumar.module10.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {

    @Pointcut("execution(* com.avinash.kumar.module10.services.impl.*.*(..)")
    public void allServiceMethods(){

    }

    @Around("allServiceMethods()")
    public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Long orderId = (Long)args[0];
        if(orderId>0) return proceedingJoinPoint.proceed();
        return "Cannot call with negative order id";
    }
    @Around("allServiceMethods()")
    public Object executionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object returnedVal = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        log.info(" run time of {} : {}",proceedingJoinPoint.getSignature(),endTime-startTime);
        return returnedVal;
    }
}
