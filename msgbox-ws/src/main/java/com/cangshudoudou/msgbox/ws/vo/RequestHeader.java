package com.cangshudoudou.msgbox.ws.vo;

import java.util.Date;

public class RequestHeader {
    private String nonce;
    private String language;
    
    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
