package com.liuyouchao.jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liuyouchao.utils.JdbcUtils;

/**
 * jdbc处理数据库中字符大数据 clob
 * mysql 通过text类型 实现clob的存储
 * 
 * 注意java 中的IO操作
 * @author Administrator
 *
 */
public class Jdbc7 {

	public static void main(String[] args) {
		//create();
		read();
	}
	/**
	 * 插入数据
	 */
	public static void create() {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "insert into clob_test(big_text) values(?)";
			ps = con.prepareStatement(sql);
			File file = new File("src/main/java/com/liuyouchao/utils/JdbcUtils.java");
			Reader reader = new BufferedReader(new FileReader(file));
			ps.setCharacterStream(1, reader);
			System.out.println("sql = "+ps.toString());
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtils.free(re, ps, con);
		}	
	}
	
	public static void read() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "select big_text from t_clob";
			ps = con.prepareStatement(sql);
			re = ps.executeQuery();
			while(re.next()) {
				Reader reader = re.getCharacterStream("big_text");
				File file = new File("test/JdbcUtils.txt");
				Writer writer = new BufferedWriter(new FileWriter(file));
				char[] c = new char[1024];
				for(int i = 0; (i = reader.read(c))>0;) {
					writer.write(c,0,i);
				}
				writer.close();
				reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.free(re, ps, con);
		}	
	}
	
		
}
