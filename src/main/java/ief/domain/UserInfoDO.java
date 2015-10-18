package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 2015/4/4.
 */
public class UserInfoDO {
    private Long id;
    private String userName;
    private Short sex;
    private Date birthday;
    private Short birthdayType;
    private String hometown;
    private String locate;
    private String career;
    private String phone;
    private String password;
    private String school;
    private String deviceInfo;
    private String signature;
    private Date createdTime;
    private String userHeadImg;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getHometown() {
        return hometown;
    }

    public String getLocate() {
        return locate;
    }

    public String getCareer() {
        return career;
    }

    public String getPhone() {
        return phone;
    }

    public String getSchool() {
        return school;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public String getSignature() {
        return signature;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public Short getSex() {
        return sex;
    }

    public Short getBirthdayType() {
        return birthdayType;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public void setBirthdayType(Short birthdayType) {
        this.birthdayType = birthdayType;
    }

    @Override
    public String toString() {
        return "UserInfoDO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", birthdayType=" + birthdayType +
                ", hometown='" + hometown + '\'' +
                ", locate='" + locate + '\'' +
                ", career='" + career + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", school='" + school + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", signature='" + signature + '\'' +
                ", createdTime=" + createdTime +
                ", userHeadImg='" + userHeadImg + '\'' +
                '}';
    }
}
