package com.liuyouchao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.liuyouchao.utils.JdbcUtils;

/**
 * java.util 包里的Date 
 * java.sql 包里的Date
 * @author Administrator
 *
 */
public class Jdbc6 {

	public static void main(String[] args) {
		
//		Date date = read(1);
//		System.out.println("date="+date);
		create("liuyouchao",new Date(),300d);
	}
	
	//读取
	public static Date read(int i) {	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		Date birthday = null;
		try {
			con = JdbcUtils.getConnection();			
			String sql = "select birthday from user where id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, i);
			re = ps.executeQuery();
			while(re.next()) {
				//birthday =  re.getDate("birthday");
				birthday  	=  new Date(re.getDate("birthday").getTime());
			}
			return birthday;
		}catch(Exception e) {	
			return null;
		}finally {
			JdbcUtils.free(re, ps, con);
		}
	}
	
	//读取
	public static void  create(String name ,Date date,double money) {	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		Date birthday = null;
		try {
			con = JdbcUtils.getConnection();			
			String sql = "insert into user(name,birthday,money) values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			//必须将java.util包里的Date类型转换成java.sql包里的Date类型
			ps.setDate(2, new java.sql.Date(date.getTime()));
			ps.setDouble(3, money);
			int i = ps.executeUpdate();
			System.out.println("i="+i);
		}catch(Exception e) {	
			e.printStackTrace();
		}finally {
			JdbcUtils.free(re, ps, con);
		}
	}
}
