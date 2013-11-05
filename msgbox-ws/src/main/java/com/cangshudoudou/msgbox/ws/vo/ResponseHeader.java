package com.cangshudoudou.msgbox.ws.vo;

import java.util.Date;

public class ResponseHeader {
//    public static final int STATUS_SUCCESS = 1;
//    public static final int STATUS_FAILED = 0;

//    private int status = STATUS_SUCCESS;
    private String nonce;
    private Date currentTimestamp = new Date();

//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Date getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(Date currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }
}
