package com.hqyj.lk.proceduredemo;

import com.hqyj.lk.util.MyDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-12-13:50
 */
public class ProcedureTest {
    public static void main(String[] args) throws SQLException {
        Connection con = new MyDataSource().getConnection();
        CallableStatement cstmt = con.prepareCall("{call pro_item(?,?,?,?)}");
        cstmt.setInt(1, 1);
        cstmt.setInt(2, 1);
        cstmt.setInt(3, 5);
        cstmt.registerOutParameter(4, Types.FLOAT);
        cstmt.execute();
        float total = cstmt.getFloat(4);
        System.out.println("总金额 = " + total);
        cstmt.close();
        con.close();
    }
}

