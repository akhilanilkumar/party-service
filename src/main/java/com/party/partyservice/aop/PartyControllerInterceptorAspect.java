package com.party.partyservice.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class PartyControllerInterceptorAspect {

    @Pointcut("execution(* com.party.partyservice.controller.*.*(..))")
    void loggingPointCut() {
    }

    @Before("loggingPointCut()")
    void before(JoinPoint joinPoint) {
        log.info("Before {} invoked", joinPoint.getSignature().getDeclaringTypeName());
    }

    @After("loggingPointCut()")
    void after(JoinPoint joinPoint) {
        log.info("After {} invoked", joinPoint.getSignature());
    }
}
