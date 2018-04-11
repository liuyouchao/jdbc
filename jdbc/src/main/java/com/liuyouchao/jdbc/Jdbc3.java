package com.liuyouchao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.liuyouchao.utils.JdbcUtils;

/**
 * 实现数据库的CRUD
 * @author Administrator
 *
 */
public class Jdbc3 {

	public static void main(String[] args) {
		try {
			//create();
			//read();
			//update();
			//delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void create() throws Exception {
		Connection con = JdbcUtils.getConnection();
		Statement st = con.createStatement();
		String sql = "insert into user(name,birthday,money) values('zhaoliu','2018-09-02',600)";
		int i = st.executeUpdate(sql);
		System.out.println("i = "+i);
		JdbcUtils.free(null, st, con);
	}
	public static void read() throws Exception {
		Connection con = JdbcUtils.getConnection();
		Statement st = con.createStatement();
		String sql ="select id,name,birthday,money from user";
		ResultSet re = st.executeQuery(sql);
		while(re.next()) {
			System.out.println(re.getObject("id")+"\t"+re.getObject("name")+"\t"
					+re.getObject("birthday")+"\t"+re.getObject("money"));
		}
		JdbcUtils.free(re, st, con);	
	}
	public static void update() throws Exception {
		Connection con = JdbcUtils.getConnection();
		Statement st = con.createStatement();
		String sql = "update user set money = money + 10";
		int i  = st.executeUpdate(sql);
		System.out.println("i="+i);
		JdbcUtils.free(null, st, con);
	}
	public static void delete() throws Exception {
		Connection con = JdbcUtils.getConnection();
		Statement st = con.createStatement();
		String sql = "delete from user where id > 2";
		int i = st.executeUpdate(sql);
		System.out.println("i="+i);
		JdbcUtils.free(null, st, con);
	}
}
