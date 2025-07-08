package com.gmmps.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Pointcut for all public methods in your service package
    @Before("execution(public * com.example.School_Management.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("➡️ Before method: {} with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @After("execution(public * com.example.School_Management.controller..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("✅ After method: {}", joinPoint.getSignature());
    }
    // ✅ After exception
    @AfterThrowing(pointcut = "execution(public * com.example.School_Management.controller..*(..))", throwing = "ex")
    public void logAfterThrowingException(JoinPoint joinPoint, Throwable ex) {
        logger.error("❌ Exception in method: {} | Exception: {}", joinPoint.getSignature(), ex.getMessage());
    }

}
