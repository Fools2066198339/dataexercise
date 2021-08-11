package com.hqyj.lk.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-09-17:47
 */
public class DBPool {
    private static String url;
    private static String user;
    private static String password;
    private static String driver = "com.mysql.jdbc.Driver";
    private static LinkedList<Connection> pool;

    static {
        try {
            Properties properties = new Properties();
            pool = new LinkedList<>();
            Class.forName(driver);
            ClassLoader classLoader = DBPool.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("jdbc.properties");
            properties.load(is);
            url = properties.getProperty("url");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
            for (int i = 0; i < 10; i++) {
                Runtime rt = Runtime.getRuntime();
                if (rt.freeMemory() / 1024 / 1024 < 227) {
                    break;
                }
                System.out.println("第"+(i+1)+"个连接对象前空闲内存数量:"+rt.freeMemory()/1024/1024+
                        "M");
                Connection connection = DriverManager.getConnection(url, user, password);
                Connection connectionWrapper = new ConnectionWapper(connection,pool);
                pool.add(connectionWrapper);
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con;

        if (pool.size() > 0) {
            con = pool.removeFirst();
        } else {
            con = DriverManager.getConnection(url, user, password);
        }
        System.out.println("当前池子中有  " + pool.size() + " 个对象");
        return con;
    }
}
