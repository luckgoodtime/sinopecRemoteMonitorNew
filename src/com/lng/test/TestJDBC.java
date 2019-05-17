package com.lng.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {
	
	    //第二步：说明JDBC驱动的名称和数据库的地址
	    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost:3306/universal_biz_lng?autoReconnect=true";
	 
	    //第三步：说明数据库的认证账户及密码
	    static final String USER = "root";
	    static final String PASS = "root";
	 
	    public static void main(String[] args) {
	        //第四步：注册JDBC驱动
	        try {
	            Class.forName(JDBC_DRIVER);
	        } catch (ClassNotFoundException e) {
	            //这里会发生类没有找到的异常！
	            e.printStackTrace();
	        }
	        //第五步：获得数据库连接
	        try {
	            Connection connection =  DriverManager.getConnection(DB_URL,USER,PASS);
	            //第六步：执行查询语句
	            Statement statement = connection.createStatement();
	            String sql = "SELECT * FROM person";
	            ResultSet rs =statement.executeQuery(sql);
	            while (rs.next())
	            {
	                String name = rs.getString("firstName");
	                String gender = rs.getString("gender");
	                System.out.println(name+":"+gender);
	            }
	            //第七步：关闭连接资源
	            rs.close();
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //这里会发生SQL异常，因为我们提供的的账户和密码不一定能连接成功
	        }
	    }

}
