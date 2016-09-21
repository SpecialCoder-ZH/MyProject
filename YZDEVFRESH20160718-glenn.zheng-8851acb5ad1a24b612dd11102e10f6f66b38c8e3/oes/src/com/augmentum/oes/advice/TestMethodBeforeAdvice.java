package com.augmentum.oes.advice;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class TestMethodBeforeAdvice implements MethodBeforeAdvice{

    private final Logger logger = Logger.getLogger(LogMethodTimeAspect.class);
    @Override
    public void before(Method arg0, Object[] args, Object target) throws Throwable {
        logger.info("before method -> " + arg0.getName() +"--invoke");
    }

}
