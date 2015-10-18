package ief.dto.results;

import com.alibaba.fastjson.JSON;
import ief.domain.CategoryDO;
import ief.enums.CategoryEnum;
import ief.enums.StatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/22.
 * 返回给客户端结果
 */
public class BaseResult<T> {
    private int status;
    private String message;
    private T data;
    public BaseResult(StatusEnum status, T data) {
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


    public T getData() {
        return data;
    }

    public void setData(T data) {
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
        System.out.println(JSON.toJSONString(baseResult));


        BaseResult baseResult1 = new BaseResult(StatusEnum.SUCCESS, list);
    }
}
