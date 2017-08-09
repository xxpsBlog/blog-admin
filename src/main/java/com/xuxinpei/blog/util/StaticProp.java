package com.xuxinpei.blog.util;

import org.slf4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StaticProp {
    public static Map<String, String> sysConfig = new HashMap<String, String>();
    public static Logger SYSTEM;
    public static boolean IS_USER_MEMCACHED = true;
    public static boolean IS_USER_UPYUN = true;
    public static ServletContext SERVLET_CONTEXT;
    public static ExecutorService execute = Executors.newFixedThreadPool(20);
    public static String[] allowFiles = {".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp"};
    public static String upYunPath;
    public static String cookieID;
    public static String WX_TOKEN = "";
    private static DataSource dataSource;

    public static Connection getDatabaseConnection() throws SQLException {
        if (dataSource == null) {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl((String) sysConfig.get("jdbc.url"));
            ds.setUsername((String) sysConfig.get("jdbc.username"));
            ds.setPassword((String) sysConfig.get("jdbc.password"));
            dataSource = ds;
        }
        return dataSource.getConnection();
    }
}