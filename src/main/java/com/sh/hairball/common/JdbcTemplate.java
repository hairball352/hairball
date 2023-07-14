package com.sh.hairball.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTemplate {

    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;


    static {

        Properties prop = new Properties();
        try {
            String filename = JdbcTemplate.class.getResource("/datasource.properties").getPath();
            prop.load(new FileReader(filename));
            driverClass = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void commit(Connection conn) {
        try {
            if(conn != null && !conn.isClosed())
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if(conn != null && !conn.isClosed())
                conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn) {

        try {
            if(conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement pstmt) {

        try {
            if(pstmt != null && !pstmt.isClosed())
                pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {

        try {
            if(rset != null && !rset.isClosed())
                rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
