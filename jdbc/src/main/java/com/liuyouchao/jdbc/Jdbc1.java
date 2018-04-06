package com.liuyouchao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver; 

/**
 * jdbc 的基础连接数据库
 * @author Administrator
 *
 */
public class Jdbc1 
{
	
	public static void main( String[] args ){
		try {
			Jdbc1.test();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  void test() throws Exception {
		//注册驱动
			//第一种方式 底层是一个注册列表，或者是数组对象 Vector列表
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//第二种方式 是通过键值对的方式,底层是HashTable驱动列表
			System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			//第三种方式  根据类的名称，将类装载到虚拟机里，会调用它本身的构造函数,创建实例对象（Driver类源码）
			Class.forName("com.mysql.jdbc.Driver");
			
		//建立连接
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		Connection con = DriverManager.getConnection(url,userName,passWord);
		//创建语句对象
		Statement st = con.createStatement();
		//执行语句
		ResultSet rs = st.executeQuery("select * from user");
		//处理结果
		while(rs.next()) {
			System.out.println(rs.getObject(1)+"\t"+rs.getObject(2)+"\t"+
					rs.getObject(3)+"\t"+rs.getObject(4));
		}
		//释放资源
		rs.close();
		st.close();
		con.close();
	}
   
}
