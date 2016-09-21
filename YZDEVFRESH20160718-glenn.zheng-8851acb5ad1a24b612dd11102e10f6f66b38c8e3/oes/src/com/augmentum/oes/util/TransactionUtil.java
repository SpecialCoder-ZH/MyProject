package com.augmentum.oes.util;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class TransactionUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private static ComboPooledDataSource ds= null;

    static{
            ds= new ComboPooledDataSource();
    }

    public static DataSource getDataSource(){
        return ds;
    }

    public static Connection getConnection(){
        try{
            Connection conn = threadLocal.get();
            if(conn==null){
                conn = ds.getConnection();
                threadLocal.set(conn);
            }
            return conn;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void startTransaction(){
        try{
             Connection conn = threadLocal.get();
             if(conn==null){
                 conn = getConnection();
             }
             conn.setAutoCommit(false);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void rollback(){
        try{
             Connection conn = threadLocal.get();
             if(conn == null){
                 conn = getConnection();
             }
             conn.rollback();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void commit(){
        try{
            Connection conn = threadLocal.get();
            if(conn==null){
                conn = getConnection();
            }
            conn.commit();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void release(){
        try{
            Connection conn = threadLocal.get();
            if(conn!=null){
                conn.close();
                threadLocal.remove();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
