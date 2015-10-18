package ief.dto.params;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangdongsheng on 15/7/12.
 */
public class UpdateUserInfoParam {
    private Long id;
    private String userName;
    private Short sex;
    private Date birthday;
    private String birthdayStr;
    private Short birthdayType;
    private String hometown;
    private String locate;
    private String career;
    private String phone;
    private String school;
    private String deviceInfo;
    private String signature;
    private String userHeadImg;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Short getSex() {
        return sex;
    }

    public Date getBirthday() {
        if(StringUtils.isEmpty(birthdayStr)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = simpleDateFormat.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthday;
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public Short getBirthdayType() {
        return birthdayType;
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

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setBirthdayType(Short birthdayType) {
        this.birthdayType = birthdayType;
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

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    @Override
    public String toString() {
        return "UpdateUserInfoParam{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", birthdayStr='" + birthdayStr + '\'' +
                ", birthdayType=" + birthdayType +
                ", hometown='" + hometown + '\'' +
                ", locate='" + locate + '\'' +
                ", career='" + career + '\'' +
                ", phone='" + phone + '\'' +
                ", school='" + school + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", signature='" + signature + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                '}';
    }
}
