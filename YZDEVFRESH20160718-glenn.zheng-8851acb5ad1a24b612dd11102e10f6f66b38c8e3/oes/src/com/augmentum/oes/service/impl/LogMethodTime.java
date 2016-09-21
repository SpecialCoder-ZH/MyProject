package com.augmentum.oes.service.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.augmentum.oes.AppContext;

public class LogMethodTime implements MethodInterceptor{

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("invoke logMethodTime....");
        long startTime = System.currentTimeMillis();

        Object returnValue = methodInvocation.proceed();
        String methodName = methodInvocation.getMethod().getName();
        long endTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        sb.append(AppContext.getAppContext().getUsername());
        sb.append(":");
        sb.append(methodInvocation.getMethod().getDeclaringClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(endTime - startTime );

        logger.info(sb.toString());
        return returnValue;
    }
}
