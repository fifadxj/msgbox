package info.cangshudoudou.msgbox;

public class BusinessException extends RuntimeException {

    private String code;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BusinessException(String code) {
        super();
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }
    
}
