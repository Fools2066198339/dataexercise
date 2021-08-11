package com.hqyj.lk.test;

import com.hqyj.lk.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-10-18:08
 */
public class TransactionTest {
    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);//关闭事务自动提交(开启事务)
            Statement stmt = con.createStatement();
            int num = stmt.executeUpdate("UPDATE  salary set money=money-2000 where id=1");
            int a =5/0;
            int num2 = stmt.executeUpdate("UPDATE  salary set " +
                    "money=money+2000 where id=2");
            System.out.println(num);
            System.out.println(num2);
            con.commit();//执行完事务，则提交
            //恢复自动提交模式
            con.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();//发生问题，则回滚
                //恢复自动提交模式
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
