package com.hqyj.lk.shop;

import java.util.Scanner;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-10-18:47
 */
public class ShopClient {
    public static void main(String[] args) {
        //初始化菜单对象
        ShopMenu s = new ShopMenu();
        //普通用户折扣
        int discount = 9;
        //初始化客户等级
        int lev = 0;
        //程序入口

        while (true) {
            //初始化界面
            s.init();
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你的选择:");
            String ch = sc.next();
            if ("1".equals(ch)) {
                lev = s.membersLoad();
            } else if ("2".equals(ch)) {
                //显示商品目录
                s.showGoods();
                //计算购买商品总和
                double totalMoney = s.settlement();
                //打印清单
                System.out.println("-------------------");
                System.out.println("购物汇总清单:");
                System.out.println("总金额:" + String.format("%.2f", totalMoney));
                System.out.println("折扣:" + (double) (discount - lev) / 10);
                System.out.println("应付金额:" + String.format("%.2f",
                        totalMoney *= ((double) (discount - lev) / 10)));
                //判断结算输入金额是否正确
                while (true) {
                    System.out.println("请输入实付金额:");
                    double money = sc.nextDouble();
                    if (money - totalMoney >= 0) {
                        System.out.println("找钱:" + String.format("%.2f", money - totalMoney));
                        break;
                    } else {
                        System.out.println("输入有误，请重新输入！");
                    }
                }
                System.out.println("购物结束！");
            } else if ("3".equals(ch)) {
                System.out.println("感谢有你！\n\n");
                s.showDiscount();
            } else if ("4".equals(ch)) {
                System.out.println("退出系统中.......\n");
                System.out.println("程序结束");
                break;
            } else {
                System.out.println("输入有误，请重新输入！");
            }
        }
    }
}
