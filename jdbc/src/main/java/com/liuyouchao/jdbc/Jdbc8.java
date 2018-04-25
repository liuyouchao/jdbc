package com.liuyouchao.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liuyouchao.utils.JdbcUtils;

/**
 *  jdbc 操作字节流 ，操作图片，压缩包等
 * @author Administrator
 *
 */
public class Jdbc8 {


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
			String sql = "insert into t_blob(content) values(?)";
			ps = con.prepareStatement(sql);
			File file = new File("test/test.jpg");
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			ps.setBinaryStream(1, in);
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
			String sql = "select content from t_blob";
			ps = con.prepareStatement(sql);
			re = ps.executeQuery();
			while(re.next()) {
				InputStream in = re.getBinaryStream("content");
				File file = new File("test/test_bak.jpg");
				OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				byte[] b = new byte[1024];
				for(int i = 0; (i = in.read(b))>0;) {
					out.write(b, 0, i);
				}
				out.close();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.free(re, ps, con);
		}	
	}
	
		
	
}
