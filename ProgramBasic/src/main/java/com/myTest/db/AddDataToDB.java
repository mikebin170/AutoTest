package com.myTest.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddDataToDB {
	
	private static Connection getConn() {
//	    String driver = "com.mysql.jdbc.Driver";
//	    String url = "jdbc:mysql://47.98.220.165:3306/Interfaces?characterEncoding=utf8&useSSL=true";
//	    String username = "root";
//	    String password = "123456";
//	    Connection conn = null;
//	    try {
//	        Class.forName(driver); //加载驱动到系统环境
//	        conn = (Connection) DriverManager.getConnection(url, username, password);
//	    } catch (ClassNotFoundException e) {
//	        e.printStackTrace();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return conn;
		try {
			return JDBCUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static int insert(String id,String name,String pwd) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into t_user (userid,username,password) values(?,?,?)";
	    PreparedStatement pstmt = null;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setString(2, name);
	        pstmt.setString(3, pwd);
	        i = pstmt.executeUpdate();
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
	    return i;
	}
	
   static class DbThread extends Thread{
    	
    	public int begin;
    	public int end;
    	

		public DbThread(int begin, int end) {
			super();
			this.begin = begin;
			this.end = end;
		}


		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " "+begin);
			for (int i = begin; i < end; i++) {
				System.out.println(Thread.currentThread().getName() + " "+i);
				//String uid =UUID.randomUUID().toString();
				int reulst = AddDataToDB.insert(""+i,"testfan"+i,"test"+i);
			}
		}
    	
    }
	
	
	public static void main(String[] args) {
		 // int reulst = AddDataToDB.insert("12311","test12211","test111");
		int allcount =10000;
		int pagesize =100;
		
		int allpage = allcount%pagesize==0?allcount/pagesize:(allcount/pagesize)+1;
		
//		if(allcount%pagesize==0) {
//			allpage = allcount/pagesize;
//		}else {
//			allpage = allcount/pagesize+1;
//		}
		
		System.out.println(allpage);
		
		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < allpage; i++) {
			int begin = i*pagesize;
			int end = (i+1)*pagesize;
			if(end>allcount) {
				end = 	allcount;
			}
			//System.out.println(" begin "+begin + " end " +end);
			//new DbThread(begin, end).start();
			cachedThreadPool.execute(new DbThread(begin, end));
		}
		
	   //cachedThreadPool.shutdown();
		
//		  for (int i = 0; i < 1000000; i++) {
//			  AddDataToDB.insert(UUID.randomUUID().toString(), "test"+i,"test"+i);
//		  }
		/*for(int i=0;i<100000;i++){
		  String uid =UUID.randomUUID().toString();
		  int reulst = AddDataToDB.insert(uid,"testfan"+i,"test"+i);
		  System.out.println("reulst " + i);
		}
		*/
		
	}
}
	
	
