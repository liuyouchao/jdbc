package com.liuyouchao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 连接数据库的工具类
 * @author Administrator
 *
 */
public final class JdbcUtils {

	//静态资源
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String userName = "root";
	private static String passWord = "root";
	
	private JdbcUtils() {}
	
	//静态代码块在类加载到虚拟机的时候执行，并且只执行一次
	//注册只需要注册一次即可
	static void registerDriver() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");	
	}
	
	//获取连接
	public static Connection getConnection() throws Exception {	
		return DriverManager.getConnection(url,userName,passWord);
	}
	
	//释放资源
	public static void free(ResultSet re,Statement st,Connection con) {
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
