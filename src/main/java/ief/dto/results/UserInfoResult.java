package ief.dto.results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangdongsheng on 15/7/12.
 */
public class UserInfoResult {
    private Long userId;
    private String userHeadImg;
    private String userName;
    private String locate;
    private Short sex;
    private Date birthday;
    private String birthdayStr;
    private short birthdayType;
    private int wantedNum;
    private int ownedNum;
    private String hometown;
    private String school;

    public Long getUserId() {
        return userId;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public String getUserName() {
        return userName;
    }

    public String getLocate() {
        return locate;
    }

    public Short getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public short getBirthdayType() {
        return birthdayType;
    }

    public int getWantedNum() {
        return wantedNum;
    }

    public int getOwnedNum() {
        return ownedNum;
    }

    public String getHometown() {
        return hometown;
    }

    public String getSchool() {
        return school;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    public void setBirthdayStr() {
        if(birthday == null) return;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.birthdayStr = simpleDateFormat.format(birthday);
    }

    public void setBirthdayType(short birthdayType) {
        this.birthdayType = birthdayType;
    }

    public void setWantedNum(int wantedNum) {
        this.wantedNum = wantedNum;
    }

    public void setOwnedNum(int ownedNum) {
        this.ownedNum = ownedNum;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "UserInfoResult{" +
                "userId=" + userId +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", userName='" + userName + '\'' +
                ", locate='" + locate + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", birthdayStr='" + birthdayStr + '\'' +
                ", birthdayType=" + birthdayType +
                ", wantedNum=" + wantedNum +
                ", ownedNum=" + ownedNum +
                ", hometown='" + hometown + '\'' +
                ", school='" + school + '\'' +
                '}';
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2015-06-21";
        try {
            System.out.println(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
