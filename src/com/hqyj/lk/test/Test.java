package com.hqyj.lk.test;

import com.hqyj.lk.util.DBUtil;

import java.sql.*;
import java.util.Scanner;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-09-9:15
 */
public class Test {
    public static void main(String[] args)  {
/*        try {
            Connection con2 = DBUtil.getConnection();
            con2.close();
            Connection con3 = DBUtil.getConnection();
            Connection con = DBUtil.getConnection();
            System.out.println(con2==con3);
            //3.创建命令
            Statement stmt = con.createStatement();
         *//*   int i = stmt.executeUpdate("insert  into stu(number,name,age) " +
                    "values (008,'小刘',23),(009,'小风',29)");
            System.out.println("受影响的行数:"+i);*//*

            ResultSet rs = stmt.executeQuery("select * from stu");


            while (rs.next()) {
                String name = rs.getString("name");
                String sex = rs.getString("sex");

                int age = rs.getInt("age");
                System.out.println(name + ":" + age+":"+sex);
            }
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }*/

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = sc.nextLine();
        System.out.println("请输入密码:");
        String password = sc.nextLine();
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            // 请查看示例 https://gist.github.com/retanoj/5fd369524a18ab68a4fe7ac5e0d121e8
            //存在SQL注入漏洞
/*            StringBuffer sql = new StringBuffer("select * from user where name ='");
            sql.append(name);
            sql.append("'and password='");
            sql.append(password);
            sql.append("'");
                        ResultSet rs = stmt.executeQuery(sql.toString());*/

            //  安全的SQL查询 ,一般查询场景
            String sql = "SELECT * FROM user WHERE name = ? and password = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2,password);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                System.out.println("登录成功!");
            }else {
                System.out.println("用户名或密码错误!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
