package com.myTest.dbUtils;

import com.myTest.db.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadDB {

    private static Connection getConn() {
        try {
            return JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> query() {
        Connection conn = getConn();
        int i = 0;
        String sql = "select * from  t_user limit 1,10";
        PreparedStatement pstmt = null;
        List<User> list =new ArrayList<User>();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rSet= pstmt.executeQuery();
            int count = 0;

            while (rSet.next()){
                User user = new User();
                System.out.print(rSet.getString("userid"));
                user.setUserid(rSet.getString("userid"));

                System.out.print(rSet.getString("username"));
                user.setUsername(rSet.getString("username"));

                System.out.print(rSet.getString("password"));
                user.setPassword(rSet.getString("password"));
//                System.out.println("------------"+ count++);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return list;
    }

    public static  void  main(String[] args){
        List<User> users =query();
        for (User user:users){
            System.out.println(user);
        }
    }
}
