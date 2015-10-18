package ief.model;

/**
 * Created by zhangdongsheng on 15/7/5.
 */
public class KeyValueModel {
    private int code;
    private String name;

    public KeyValueModel(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "KeyValueModel{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
