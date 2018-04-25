package com.liuyouchao.jdbc17.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liuyouchao.jdbc17.dao.OperateDao;
import com.liuyouchao.utils.DaoException;
import com.liuyouchao.utils.JdbcUtils;

/**
 * dao层的实现类
 * @author Administrator
 *
 */
public class OperateDaoImpl implements OperateDao{

	@Override
	public void create() {
		Connection  con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		try {
			con = JdbcUtils.getConnection();
			String sql ="insert into user (name,birthday,money) values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, "liuyouchao");
			ps.setDate(2, new Date(System.currentTimeMillis()));
			ps.setString(3, "0.0");
			ps.executeQuery();
			
		} catch (Exception e) {
			//处理异常时，决不能仅仅打印异常堆栈信息，这样是不合格的，系统会继续执行，异常会转移，所以异常还需要手动处理
			//方式 1 将异常信息抛出，在方法上加上抛出异常，这样调用层必须进行异常处理，同时也破坏了接口的隔离性
			//方式2 自己生命一个运行时异常，在catch住异常之后，将编译时异常转换成运行时异常，抛出给调用者，
			//这样调用者可以处理异常，也可以不处理异常，实现异常信息的通知，同时满足接口的隔离性
			//例如
			// 首先打印异常堆栈信息
			e.printStackTrace();
			// 其次将编译时异常转成运行时异常
			throw new DaoException(e.getMessage(),e);
			
		}finally {
			JdbcUtils.free(re, ps, con);
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	
	
}
