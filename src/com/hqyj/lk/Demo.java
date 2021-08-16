package com.hqyj.lk;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-09-18:36
 */
public class Demo {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        //创建连接池实例
        ComboPooledDataSource ds = new ComboPooledDataSource();
        //设置数据库的驱动
        ds.setDriverClass("com.mysql.jdbc.Driver");
        //设置数据库的地址
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        //设置连接数据库的用户名
        ds.setUser("root");
        //设置连接数据库的密码
        ds.setPassword("root");
        //设置连接池的初始连接数
        ds.setInitialPoolSize(6);
        //设置连接池最多有多少连接数
        ds.setMaxPoolSize(40);
        //设置连接池中最少连接数
        ds.setMinPoolSize(2);
        //设置连接池缓存Statement的最大数
        ds.setMaxStatements(180);

        Connection connection = ds.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from stu");

        while (rs.next()) {
            String name = rs.getString("name");
            String sex = rs.getString("sex");

            int age = rs.getInt("age");
            System.out.println(name + ":" + age+":"+sex);
        }


/*        //获取Runtime类对象
        Runtime rt = Runtime.getRuntime();

        //rt.availableProcessors() 返回java虚拟机中可用处理器的数量
        System.out.println("处理器的个数：" + rt.availableProcessors());
        //返回Java虚拟机中的空闲内存量
        System.out.println("空闲内存数量" + rt.freeMemory() / 1024 / 1024 + "M");
        //最大内存数量
        System.out.println("最大内存数量" + rt.maxMemory() / 1024 / 1024 + "M");

        Connection c1 = DBPool.getConnection();
        Connection c2 = DBPool.getConnection();
        Connection c3 = DBPool.getConnection();
        Connection c4 = DBPool.getConnection();
        c3.close();
        Connection c5 = DBPool.getConnection();
        Connection c6 = DBPool.getConnection();
        Connection c7 = DBPool.getConnection();
        Connection c8 = DBPool.getConnection();
        Connection c9 = DBPool.getConnection();
        c9.close();
        Connection c10 = DBPool.getConnection();
        Connection c11 = DBPool.getConnection();*/

    }
}
