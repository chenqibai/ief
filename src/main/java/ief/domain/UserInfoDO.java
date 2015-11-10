package ief.domain;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import ief.utils.DateUtil;

/**
 * Created by zhangdongsheng on 2015/4/4.
 */
public class UserInfoDO {
    private Long userId;
    private String userName;
    private Short sex;
    private String birthday;
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
    private Date lunarBirthday;
    
    private String constellation;
    private String defaultPlace;//默认见面地点
    private BigDecimal lon;//经度
    private BigDecimal lat;//纬度
    private String district;//区
    private String street;//街道
    private String city;//城市
    private Integer locationFlag;//是否修改咖啡厅
    private Integer birthdayFlag;//是否修改生日
    private Integer wantedNum;
    private Integer ownedNum;

    public Integer getWantedNum() {
		return wantedNum;
	}
	public void setWantedNum(Integer wantedNum) {
		this.wantedNum = wantedNum;
	}
	public Integer getOwnedNum() {
		return ownedNum;
	}
	public void setOwnedNum(Integer ownedNum) {
		this.ownedNum = ownedNum;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getDefaultPlace() {
		return defaultPlace;
	}
	public void setDefaultPlace(String defaultPlace) {
		this.defaultPlace = defaultPlace;
	}
	public BigDecimal getLon() {
		return lon;
	}
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getLocationFlag() {
		return locationFlag;
	}
	public void setLocationFlag(Integer locationFlag) {
		this.locationFlag = locationFlag;
	}
	public Integer getBirthdayFlag() {
		return birthdayFlag;
	}
	public void setBirthdayFlag(Integer birthdayFlag) {
		this.birthdayFlag = birthdayFlag;
	}
	public Date getLunarBirthday() {
		return lunarBirthday;
	}
	public void setLunarBirthday(Date lunarBirthday) {
		this.lunarBirthday = lunarBirthday;
	}
	public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setBirthday(String birthday) {
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
    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
        return userName;
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
    public Date getBirthday() throws ParseException{
    	if(birthday!=null)
    		return DateUtil.getyyyyMMddDate(birthday);
    	else
    		return null;
    }
    public String getFormatBirthday(){
        return birthday;
    }
    @Override
    public String toString() {
        return "UserInfoDO{" +
                "userId=" + userId +
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
