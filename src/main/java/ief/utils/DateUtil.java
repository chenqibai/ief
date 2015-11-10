package ief.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/24.
 */
public class DateUtil {
    public static String getNowYYYYmmDD(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
    public static String YYYY_MM_DD="yyyy-MM-dd";
    
    public static Date getyyyyMMddDate(String day) throws ParseException{
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
	    return simpleDateFormat.parse(day);
    }
    
    public static void main(String[] args) {
        System.out.println(getNowYYYYmmDD());
    }
}
