package ief.enums;

/**
 * Created by zhangdongsheng on 15/6/22.
 * 书籍是否借出
 */
public enum OnLoanEnum {
    /**
     * 借出
     */
    LOAN(1),
    /**
     * 未借出
     */
    NO_LOAN(0);

    private int code;

    private OnLoanEnum(int code){
        this.code = code;
    }
}
