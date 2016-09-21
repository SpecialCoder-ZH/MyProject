package com.augmentum.oes.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.augmentum.oes.exception.DBException;

public class DBUtil {

   private static String jdbcUrl;
   private static String userName;
   private static String password;
   private static String drivername;

   static {
       InputStream in = null;
        try {
            Properties prop = new Properties();
            in = DBUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            prop.load(in);
            jdbcUrl = prop.getProperty("url");
            userName = prop.getProperty("username");
            password = prop.getProperty("password");
            drivername=prop.getProperty("driverClassName");
            Class.forName(drivername);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return conn;
    }

    public static void release(Connection conn, PreparedStatement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
