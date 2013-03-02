package com.cangshudoudou.msgbox.ws.vo;

import java.util.Date;

public class BaseResponse {
	public static final int STATUS_SUCCESS = 1;
	public static final int STATUS_FAILED = 0;

	private int status = STATUS_SUCCESS;
	private String nonce;
	private String errorCode;
	private String errorMessage;
	private Date currentTimestamp = new Date();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(Date currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}

	public static int getStatusSuccess() {
		return STATUS_SUCCESS;
	}

	public static int getStatusFailed() {
		return STATUS_FAILED;
	}
}
