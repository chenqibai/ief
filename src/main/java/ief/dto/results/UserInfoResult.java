package ief.dto.results;

import java.math.BigDecimal;
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
    private Integer sex;
    private Date birthday;
    private Date lunarBirthday;
    private short birthdayType;
    private int wantedNum;
    private int ownedNum;
    private String hometown;
    private String school;
    private String district;//区
    private String street;//街道
    private String city;//城市
    private String defaultPlace;//默认见面地点
    private String constellation;//星座
    private String career;//职业
    private String signature;//签名
    private String currentCity;
    private BigDecimal lon;//经度
    private BigDecimal lat;//纬度
    
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
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public Date getLunarBirthday() {
		return lunarBirthday;
	}
	public void setLunarBirthday(Date lunarBirthday) {
		this.lunarBirthday = lunarBirthday;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
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
	public String getDefaultPlace() {
		return defaultPlace;
	}
	public void setDefaultPlace(String defaultPlace) {
		this.defaultPlace = defaultPlace;
	}
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
    public Date getBirthday() {
        return birthday;
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
    public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
