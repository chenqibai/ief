package ief.dto.results;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/7/4.
 */
public class LoginResult {
    private Long userId;
    private Integer wanted;
    private Integer owned;
    private String userName;
    private Short sex;
    private Date birthday;
    private Short birthdayType;
    private String hometown;
    private String locate;
    private String sessionId;
    private String userHeadImg;

    public Short getBirthdayType() {
		return birthdayType;
	}
	public void setBirthdayType(Short birthdayType) {
		this.birthdayType = birthdayType;
	}
	public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setWanted(Integer wanted) {
        this.wanted = wanted;
    }

    public void setOwned(Integer owned) {
        this.owned = owned;
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

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public Integer getWanted() {

        return wanted;
    }

    public Integer getOwned() {
        return owned;
    }

    public String getUserName() {
        return userName;
    }

    public Short getSex() {
        return sex;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "userId=" + userId +
                ", wanted=" + wanted +
                ", owned=" + owned +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", birthdayType=" + birthdayType +
                ", hometown='" + hometown + '\'' +
                ", locate='" + locate + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                '}';
    }
}
