package ief.enums;

/**
 * Created by zhangdongsheng on 15/6/22.
 * 性别枚举
 */
public enum SexEnum {
    /**
     * 男性
     */
    MALE(1),
    /**
     * 女性
     */
    FEMALE(2);

    private int code;

    private SexEnum(int code){
        this.code = code;
    }
}
