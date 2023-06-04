package com.rudra.musicStreaming.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class) ;

    @Pointcut("within(com.rudra.musicStreaming.controllers.*)")
    public void pointCut(){

    }

    @Before("execution(* com.rudra.musicStreaming.controllers.*.*(..))")
    public void logMethod (JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature() ;

        // intercepted method details
//        String packageName = signature.getDeclaringTypeName() ;
        String className = signature.getClass().getSimpleName() ;
        String methodName = signature.getName() ;
//        logger.warn(">>> Requested method - " + methodName + ", from class - " + className);
        logger.warn(">>> Requested method [.{}, {}]", methodName , className);
    }
}
