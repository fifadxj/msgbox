package com.cangshudoudou.msgbox.ws.vo;

import java.util.Date;

public class BaseResponse {
    private ResponseHeader responseHeader;

    public BaseResponse() {
        responseHeader = new ResponseHeader();
    }
    
    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }
    
}
