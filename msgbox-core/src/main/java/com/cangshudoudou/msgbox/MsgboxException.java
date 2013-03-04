package com.cangshudoudou.msgbox;

public class MsgboxException extends RuntimeException {
    private String code;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MsgboxException() {
        super();
    }
    
    public MsgboxException(String code) {
        super(code);
        this.code = code;
    }

    public MsgboxException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public MsgboxException(String code, String message) {
        super(message);
        this.code = code;
    }

    public MsgboxException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
    }

    public MsgboxException(Throwable cause) {
        super(cause);
    }
    
}
