package com.hqyj.lk.shop;

import com.hqyj.lk.util.DBUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @AUTHOR LK
 * @CREATE 2021-08-10-18:43
 */
public class ShopMenu {
    /**
     * 存放商品信息 goods
     * 存放商品价格 prices
     * 存放顾客星级 custom
     */
    private static HashMap<Integer, String> goods;
    private static LinkedList<Double> prices;
    private static HashMap<String, Integer> custom;
    private static LinkedList<Integer> repertory;

    //从数据库中读取数据
    static {
        Connection con = DBUtil.getConnection();
        goods = new HashMap<>();
        prices = new LinkedList<>();
        custom = new HashMap<>();
        repertory = new LinkedList<>();
        try {
            //关闭事务自动提交(开启事务)
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from goods");

            while (rs.next()) {
                goods.put(rs.getInt("id"), rs.getString("name"));
                prices.add(rs.getDouble("price"));
                repertory.add(rs.getInt("repe"));
            }
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
        try {
            //关闭事务自动提交(开启事务)
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            ResultSet rs2 = stmt.executeQuery("select * from custom");
            while (rs2.next()) {
                custom.put(rs2.getString("name"), rs2.getInt("is_members"));
            }
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

    /**
     * 初始化菜单列表
     */

    public void init() {
        System.out.println("=============");
        System.out.println("***************");
        System.out.println("*  1.会 员 登 录");
        System.out.println("*  2.购 物 结 算");
        System.out.println("*  3.真 情 回 馈");
        System.out.println("*  4.退 出 系 统");
        System.out.println("***************");
    }

    /**
     * 会员登录
     */
    public int membersLoad() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = sc.nextLine().trim();
        System.out.println("请输入密码:");
        String password = sc.nextLine().trim();
        try {
            Connection con = DBUtil.getConnection();
            //  安全的SQL查询 ,一般查询场景
            String sql = "SELECT * FROM custom WHERE name = ? and password = ?";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                System.out.println("登录成功!");
                System.out.print("会员名:" + name + " 星级:");
                for (int i = 0; i < custom.get(name); i++) {
                    System.out.print("☆");
                }
                System.out.println();
                return custom.get(name);
            } else {
                System.out.println("用户名或密码错误!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 显示商品信息
     */
    public void showGoods() {
        System.out.println("<<<<<<<<<<<<>>>>>>>>>>>>>>");
        System.out.println("         商品列表           ");
        System.out.println();
        System.out.println("序号    商品名      价格      库存");
        int index = 0;
        for (Integer key : goods.keySet()) {
            System.out.print(key + "      " + goods.get(key));
            printSpaces("商品名       ".length() - goods.get(key).length());
            System.out.print(prices.get(index)+"      "+repertory.get(index));
            System.out.println();
            index++;
        }
        System.out.println("#######################");

    }

    /**
     * 商品输出格式
     */
    public static void printSpaces(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print(" ");
        }
    }

    /**
     * 商品结算
     */
    public double settlement() {
        double sum = 0.0;
        String flag = "y";
        while ("y".equals(flag)) {
            Scanner sc = new Scanner(System.in);

            int ch=0;
            while (true) {
                System.out.println("请输入商品的编号:");
                ch= sc.nextInt();
                if (ch < 0 || ch > repertory.size() - 1) {
                    System.out.println("该商品不存在！");
                } else {
                    break;
                }
            }
            int count = 0;
            while (true) {

                System.out.println("请输入商品的数量:");
                 count= sc.nextInt();
                if (repertory.get(ch-1) < count) {
                    System.out.println("库存不足，请重新输入！");
                } else {
                    Connection con = DBUtil.getConnection();
                    try {
                        PreparedStatement pre = con.prepareStatement("update " +
                                "goods set repe=? where id = ?");
                        pre.setInt(1,(repertory.get(ch-1)-count));
                        pre.setInt(2,ch);
                        pre.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    repertory.set(ch-1, (repertory.get(ch-1) - count));
                    break;
                }
            }
                System.out.println("你选择了:");
                System.out.println("       名称:" + goods.get(ch));
                System.out.println("       单价:" + prices.get(ch - 1));
                System.out.println("       数量:" + count);
                System.out.println("       合计:" + String.format("%.2f",
                        prices.get(ch - 1) * count));
                sum += prices.get(ch - 1) * count;

            System.out.println("是否还需要购买其他商品？(y/n)");
            flag = sc.next();
            new ShopMenu().showGoods();
        }
        return sum;
    }

    /**
     * 显示打折信息
     */
    public void showDiscount() {
        System.out.println("普通客户享受九折优惠！\n" +
                "一星会员享受八折优惠！\n" +
                "两星会员享受七折优惠！\n" +
                "三星会员享受六折优惠！\n" +
                "四星会员享受五折优惠！\n");
    }
}
