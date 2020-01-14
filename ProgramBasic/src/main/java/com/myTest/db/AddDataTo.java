package com.myTest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//原始添加数据

public class AddDataTo {
    public static Connection getCon() {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://47.98.220.165:3306/Interfaces?characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int insert(String id, String name, String passwd) {
        int i = 0;
        Connection conn = getCon();
        String sql = "insert into t_user (userid,username,password) values(?,?,?)";
        PreparedStatement psmt = null;

        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1, id);
            psmt.setString(2, name);
            psmt.setString(3, passwd);
            i = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    static class DbThread extends Thread {

        public int begin;
        public int end;


        public DbThread(int begin, int end) {
            super();
            this.begin = begin;
            this.end = end;
        }


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " " + begin);
            for (int i = begin; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                //String uid =UUID.randomUUID().toString();
                int reulst = AddDataToDB.insert("" + i, "testfan" + i, "test" + i);
            }
        }

    }

}
