package com.hqyj.lk.test;

import com.hqyj.lk.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-10-15:05
 * 获取最大id
 * 1.select LAST_INSERT_ID()
 * 2.Statement.RETURN_GENERATED_KEYS stmt.getGeneratedKeys()
 */
public class TestGetMaxId {
    public static void main(String[] args)   {
        Connection con = DBUtil.getConnection();
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("insert into user(name,password) value" +
                    "('Tom','123')",Statement.RETURN_GENERATED_KEYS);
            System.out.println("受影响的行数:" + i);
            ResultSet rs = stmt.getGeneratedKeys();

//            ResultSet rs = stmt.executeQuery("select LAST_INSERT_ID()");
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("最大的id:" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
