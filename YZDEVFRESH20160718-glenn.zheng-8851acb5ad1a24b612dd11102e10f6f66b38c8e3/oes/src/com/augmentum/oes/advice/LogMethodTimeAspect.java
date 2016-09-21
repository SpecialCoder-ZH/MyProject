package com.augmentum.oes.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.augmentum.oes.AppContext;

public class LogMethodTimeAspect {

    private final Logger logger = Logger.getLogger(LogMethodTimeAspect.class);

    public void doAfter(JoinPoint jp) {
        logger.debug("log method end-->"+jp.getTarget().getClass().getName()+"."+jp.getSignature().getName());
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = pjp.proceed();
        String methodName = pjp.getSignature().getName();
        long endTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(AppContext.getAppContext().getUsername());
        sb.append(":");
        sb.append(pjp.getTarget().getClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(endTime - startTime );

        logger.info(sb.toString());
        return returnValue;
    }

    public void doThrow(JoinPoint jp, Throwable ex ) {
        logger.debug("log method exception-->"+jp.getTarget().getClass().getName()+"."+jp.getSignature().getName()+"throw exception");
    }
}
