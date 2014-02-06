package com.iut;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class Singleton
{
    public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mariadb://localhost/javaBDD";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final DataSource DS = new BasicDataSource();

    static {
        BasicDataSource ds = (BasicDataSource)DS;
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setUrl(JDBC_URL);
        ds.setDefaultAutoCommit(false);
        ds.setDefaultTransactionIsolation(2);
    }
}
