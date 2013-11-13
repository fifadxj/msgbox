package com.cangshudoudou.msgbox.ws.vo;

import java.util.Date;

public class BaseResponse {
    private ResponseHeader header;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public BaseResponse() {
        header = new ResponseHeader();
    }
    
}
