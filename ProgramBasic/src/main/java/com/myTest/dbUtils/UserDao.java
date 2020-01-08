package com.myTest.dbUtils;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.myTest.db.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserDao {
    public static List<User> getAll() throws SQLException {
        //使用dbutils查询数据库更简单
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from t_user ";
        List<User> list = (List) runner.query(sql, new BeanListHandler(User.class));
        return list;
    }

    public static void delete(String  uid) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from t_user where uid =? ";
        runner.update(sql,uid);
    }

    public static void delete(String  userid,String username) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from t_user where userid =? and username=?";
//        runner.update(sql,new Object[] {});
        runner.update(sql,userid,username);
    }

    public static User getById(String id) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from t_user where userid=?";
        User user=  (User) runner.query(sql, new BeanHandler(User.class),id);
        return user;
    }

    public static List getallObject() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from t_user";
        List list = runner.query(sql, new ArrayListHandler());
        return list;
    }

    public static void batch() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into t_user(userid,username,password) values(?,?,?)";
        //new-10行3列,for循环行,往每行的第一、二、三列往里塞数据
        Object[][] params = new Object[5][3];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[]{UUID.randomUUID().toString(), "username"+i, "password"+i};
        }
        runner.batch(sql, params);
    }

    public static  void  main(String[] args) throws SQLException {
//        try {
//            List<User> users=UserDao.getAll();
//
//          for (User user:users){
//              System.out.println(user);
//          }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //使用hutool.db那么UserDao都省了,更便捷
//        List<Entity> results = Db.use().query("select * from t_user ");
//        for (Entity entity:results){
//            System.out.println(entity);
//        }

//        delete("7c909e84-31d7-4e8d-9cea-e5f0ed010572","testfan6");
        User users=UserDao.getById("070c96e4-7bc7-4f54-8c2d-370959b1cac5");
        System.out.println(users);
    //查询结果返回list集合,list是行,Object []是列
//       List<Object []> lists= UserDao.getallObject();
//       for (Object[] list:lists){
//           System.out.println(list[0] + "-----" + list[1] + "----" +list[2]);
//       }

        UserDao.batch();
    }
}
