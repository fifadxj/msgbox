package com.cangshudoudou.msgbox;

public class BusinessException extends MsgboxException {

	public BusinessException(String code, String message, Throwable cause) {
		super(code, message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String code, Throwable cause) {
		super(code, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String code) {
		super(code);
		// TODO Auto-generated constructor stub
	}


}
