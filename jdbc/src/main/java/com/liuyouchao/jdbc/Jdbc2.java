package com.liuyouchao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.liuyouchao.utils.JdbcUtils;

/**
 * 完善一点的jdbc连接数据库
 * 使用了一个工具类
 * @author Administrator
 *
 */
public class Jdbc2 {

	public static void main(String[] args) {
		Jdbc2  jdbc = new Jdbc2();
		try {
			jdbc.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void test() throws Exception {
			
		//获取连接(获取连接的同时，驱动也被注册了)
		Connection con = JdbcUtils.getConnection();
		
		//创建语句对象
		Statement st = con.createStatement();
		
		//执行语句
		ResultSet re = st.executeQuery("select * from user");
		
		//处理结果
		while(re.next()) {
			System.out.println(re.getObject(1)+"\t"+re.getObject(2)+"\t"+re.getObject(3)+"\t"+re.getObject(4));
		}
		
		//释放资源
		JdbcUtils.free(re, st, con);
		
	}
	
}

