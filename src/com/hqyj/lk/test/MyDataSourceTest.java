package com.hqyj.lk.test;

import com.hqyj.lk.util.MyDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by lk on $(DATE)
 */
class MyDataSourceTest {

    @BeforeEach
    void setUp() {
        System.out.println("开始测试！");
    }

    @AfterEach
    void tearDown() {
        System.out.println("测试结束！");
    }

    @Test
    void getConnection() {
        MyDataSource ds = new MyDataSource();
        try {
            System.out.println(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetConnection() {
        MyDataSource ds = new MyDataSource();
        try {
            System.out.println(ds.getConnection("root","root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}