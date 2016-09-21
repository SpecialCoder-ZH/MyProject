package com.augmentum.oes.advice;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class TestMethodAfter implements AfterReturningAdvice{

    private final Logger logger = Logger.getLogger(LogMethodTimeAspect.class);
    @Override
    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
        logger.info("ClassName-->" + arg0.getClass().getSimpleName() + "MethodName-->" + arg1.getName());
    }

}
