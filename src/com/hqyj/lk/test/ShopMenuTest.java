package com.hqyj.lk.test;

import com.hqyj.lk.shop.ShopMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by lk on $(DATE)
 */
class ShopMenuTest {

    @BeforeEach
    void setUp() {
        System.out.println("开始测试");
    }

    @AfterEach
    void tearDown() {
        System.out.println("测试结束");
    }

    @Test
    void init() {
        new ShopMenu().init();
    }

    @Test
    void membersLoad() {
        new ShopMenu().membersLoad();
    }

    @Test
    void showGoods() {
        new ShopMenu().showGoods();
    }

    @Test
    void settlement() {
        new ShopMenu().settlement();
    }

    @Test
    void showDiscount() {
        new ShopMenu().showDiscount();
    }
}