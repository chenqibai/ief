package ief.dto.results;

import java.util.ArrayList;
import java.util.Date;

import ief.domain.CategoryDO;
import ief.enums.StatusEnum;
import ief.utils.JsonUtil;

/**
 * Created by zhangdongsheng on 15/6/22.
 * 返回给客户端结果
 */
public class BaseResult {
    private int status;
    private String message;
    private Object data;
    public BaseResult(StatusEnum status, Object data) {
        this.status = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public BaseResult(StatusEnum status) {
        this.status = status.getCode();
        this.message = status.getMessage();
        data = null;
    }


    public BaseResult(int status, String message){
        this.status = status;
        this.message = message;
        data = null;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static void main(String[] args) {
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setCategoryName("科幻");
        categoryDO.setCreatedTime(new Date());
        categoryDO.setId(new Long(3));

        CategoryDO categoryDO1 = new CategoryDO();
        categoryDO1.setCategoryName("科幻");
        categoryDO1.setCreatedTime(new Date());
        categoryDO1.setId(new Long(4));

        CategoryDO categoryDO2 = new CategoryDO();
        categoryDO2.setCategoryName("科幻");
        categoryDO2.setCreatedTime(new Date());
        categoryDO2.setId(new Long(5));

        ArrayList<CategoryDO> list = new ArrayList<>();
        list.add(categoryDO);
        list.add(categoryDO);
        list.add(categoryDO2);


        BaseResult baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        System.out.println(JsonUtil.toString(baseResult));


        BaseResult baseResult1 = new BaseResult(StatusEnum.SUCCESS, list);
    }
}
