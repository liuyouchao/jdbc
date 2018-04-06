package com.liuyouchao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 单例模式实现
 * 连接数据库的工具类
 * @author Administrator
 *
 */
public final class JdbcUtilsSingle {

	private  String url = "jdbc:mysql://localhost:3306/jdbc";
	private  String userName = "root";
	private  String passWord = "root";
	//初始化加载，饿汉模式
	//private  static JdbcUtilsSingle JdbcUtilsSimgle = new JdbcUtilsSingle();
	//延迟加载，懒汉模式
	private static JdbcUtilsSingle instant = null;
	
	private JdbcUtilsSingle() {}
	
	//提供对外获取单个对象的方法
	public static JdbcUtilsSingle getJdbcUtil() {
		//return JdbcUtilsSimgle;
		if(null == instant) {
			synchronized(JdbcUtilsSingle.class) {
				if(null == instant) {
					instant = new JdbcUtilsSingle(); 
				}
			}
		}
		return instant;
	}
	//静态代码块在类加载到虚拟机的时候执行，并且只执行一次
	//注册只需要注册一次即可
	static void registerDriver() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");	
	}
	
	//获取连接
	public  Connection getConnection() throws Exception {	
		return DriverManager.getConnection(url,userName,passWord);
	}
	
	//释放资源
	public  void free(ResultSet re,Statement st,Connection con) {
		if(null != re) {
			try {
				re.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(null != st) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
	}
}
