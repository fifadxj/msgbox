package com.cangshudoudou.msgbox.ws.vo;

public class BaseRequest {
    private RequestHeader header;

    public BaseRequest() {
        header = new RequestHeader();
    }
    
    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }
}
