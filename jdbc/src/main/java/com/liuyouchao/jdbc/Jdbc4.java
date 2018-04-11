package com.liuyouchao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.liuyouchao.utils.JdbcUtils;

/**
 *  sql 注入
 * @author Administrator
 *
 */
public class Jdbc4 {

	public static void main(String[] args) {
		
			//read("lisi");
			read("' or 1=1 or '");
		
	}
	public static void read(String name) {
		Connection con  = null;
		Statement st  = null;
		ResultSet re = null;
		try {
			con = JdbcUtils.getConnection();
			//出现sql注入的方式
			st = con.createStatement();
			String sql = "select id,name,birthday,money from user where name ='"+ name +"'";
			System.out.println("sql ="+sql);
			 re = st.executeQuery(sql);
			while(re.next()) {
				System.out.println(re.getObject("id")+"\t"+re.getObject("name")+"\t"
						+re.getObject("birthday")+"\t"+re.getObject("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.free(re, st, con);
		}	
	}
}
