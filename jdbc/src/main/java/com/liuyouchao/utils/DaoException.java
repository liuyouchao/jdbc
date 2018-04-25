package com.liuyouchao.utils;

/**
 *  自己定义的异常类，将编译时异常转换成运行时异常
 * @author Administrator
 *
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
