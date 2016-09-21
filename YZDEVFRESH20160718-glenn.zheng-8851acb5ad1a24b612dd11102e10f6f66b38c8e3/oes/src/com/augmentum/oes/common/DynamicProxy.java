package com.augmentum.oes.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler{

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }


    // dang bei dai li de duixiang zhixing shi hui xian zhixing daili duixiang de fang fa
    // ye jiu shi zhe ge fangfa
    // zhe ge lei shi daili lei
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       /* Object restult = null;
        System.out.println("The proxyObject's method has execute......");
        ConnectionHolder connectionHolder = (ConnectionHolder) AppContext.getAppContext().getObject("CONNECTIONHOLDER");
        boolean  needMyClose = false;
        if (connectionHolder == null) {
            Connection conn = (Connection) TransactionUtil.getConnection();
            connectionHolder = new ConnectionHolder();
            connectionHolder.setConn(conn);
            if (method.getName().equals("save")) {
                TransactionUtil.startTransaction();
                connectionHolder.setIsSrartTran(true);
            }
            AppContext.getAppContext().addObject("CONNECTIONHOLDER", conn);
            needMyClose = true;
        } else {
            if (!connectionHolder.getIsSrartTran()) {
                connectionHolder.setIsSrartTran(true);
                TransactionUtil.startTransaction();
            }
        }

        try{
            restult = method.invoke(target, args);
            if (method.getName().equals("save")) {
                TransactionUtil.commit();
            }
        } catch (Throwable throwable ) {
            if (method.getName().equals("save")) {
                TransactionUtil.rollback();
            }
            throw throwable;
        }
        return restult;*/
        Object restult = null;
        try{
            restult = method.invoke(target, args);
        } catch (InvocationTargetException targetException) {
            throw targetException.getTargetException();
        }
        return restult;
    }
}
