package com.nf.library.execption;

/**
 * 对dao层依赖进行管理
 * @author Sam
 */
public class DaoException extends RuntimeException {


	public DaoException() { };

	public DaoException(String message) {
		super(message);
	};

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	};
}
