package com.liuyouchao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liuyouchao.utils.JdbcUtils;

/**
 * 利用PreparedStatement 解决sql注入
 * 建议以后所有的开发都使用PreparedStatement 对象
 * @author Administrator
 *
 */
public class Jdbc5 {

	public static void main(String[] args) {
		read("lisi");
		//read("' or 1=1 or '");
	}
	
	public static void read (String name) {
		
		Connection con = null;
		PreparedStatement  ps = null;
		ResultSet rs = null;
		try {
			//获取连接
			con = JdbcUtils.getConnection();
			//sql语句
			String sql = "select id,name,birthday,money from user where name = ?";
			//创建语句(预编译sql语句)
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			//打印sql
			System.out.println("sql = "+ps.toString());
			//执行语句
			rs = ps.executeQuery();
			//处理结果
			while(rs.next()) {
				System.out.println(rs.getObject("id")+"\t"+rs.getObject("name")+"\t"
						+rs.getObject("birthday")+"\t"+rs.getObject("money"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.free(rs, ps, con);
		}
	}
}
