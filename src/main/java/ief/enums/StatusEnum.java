package ief.enums;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public enum StatusEnum {
    SUCCESS(0, "成功"),
    LOGIN_ERROR(-1, "登陆失效，请重新登陆"),
    FAILED(1, "失败");

    private int code;
    private String message;

    private StatusEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }
}
