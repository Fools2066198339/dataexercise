package com.hqyj.lk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-09-14:54
 */
public class DBUtil {
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String password = "root";
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection con = null;

    private DBUtil() {
    }
    // jdbc4.0 是不用显式的去加载驱动，如果驱动包符合 SPI 模式就会自动加载
/*    static {
        //1.加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    public static Connection getConnection() {
        try {
            //2.获得连接
            //新版本驱动需要加
            // &characterEncoding=utf-8&useSSL=false&serverTimezone=Hongkong&useUnicode=true
            if (con == null ||con.isClosed()) {
                con = DriverManager.getConnection(url, user, password);
                return con;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
