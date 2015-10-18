package ief.dto.params;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangdongsheng on 15/7/4.
 */
public class RegisterDetailUserParam {
    private Long userIdParam;
    private String userName;
    private String birthday;
    private String userHeadImg;
    /**
     * 0阳历，1农历
     */
    private Short birthdayType;


    public String getUserName() {
        return userName;
    }


    public String getUserHeadImg() {
        return userHeadImg;
    }

    public Short getBirthdayType() {
        return birthdayType;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public void setBirthdayType(Short birthdayType) {
        this.birthdayType = birthdayType;
    }

    public Long getUserIdParam() {
        return userIdParam;
    }

    public void setUserIdParam(Long userIdParam) {
        this.userIdParam = userIdParam;
    }

    public Date getBirthday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "RegisterDetailUserParam{" +
                "userIdParam=" + userIdParam +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", birthdayType=" + birthdayType +
                '}';
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(simpleDateFormat.parse("2015-05-02"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
