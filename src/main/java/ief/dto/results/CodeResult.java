package ief.dto.results;

/**
 * Created by zhangdongsheng on 15/7/23.
 */
public class CodeResult {
    private int code;
    private String msg;

    public CodeResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
