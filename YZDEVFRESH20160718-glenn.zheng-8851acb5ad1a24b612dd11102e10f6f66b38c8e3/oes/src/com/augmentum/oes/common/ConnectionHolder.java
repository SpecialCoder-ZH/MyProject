package com.augmentum.oes.common;

import java.sql.Connection;

public class ConnectionHolder {

    private Connection conn;
    private Boolean isSrartTran;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Boolean getIsSrartTran() {
        return isSrartTran;
    }

    public void setIsSrartTran(Boolean isSrartTran) {
        this.isSrartTran = isSrartTran;
    }
}
