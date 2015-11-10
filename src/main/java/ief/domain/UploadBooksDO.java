package ief.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class UploadBooksDO {
    private Long bookId;
    private Long userId;
    private Short onLoan;
    private String comment;
    private String bookName;
    private Long wantedNum;
    private Short category;
    private Date createdTime;
    private String bookCoverImg;
    private BigDecimal lon;//经度
    private BigDecimal lat;//纬度
    private String district;
    private String street;
    private String city;
    private String defaultPlace;
    private String constellation;
    private String userHeadImg;
    private int sex;
    private String username;

    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getUserHeadImg() {
		return userHeadImg;
	}
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getDefaultPlace() {
		return defaultPlace;
	}
	public void setDefaultPlace(String defaultPlace) {
		this.defaultPlace = defaultPlace;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public Short getOnLoan() {
        return onLoan;
    }

    public String getComment() {
        return comment;
    }

    public String getBookName() {
        return bookName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOnLoan(Short onLoan) {
        this.onLoan = onLoan;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getWantedNum() {
        return wantedNum;
    }

    public void setWantedNum(Long wantedNum) {
        this.wantedNum = wantedNum;
    }

    public Short getCategory() {
        return category;
    }

    public void setCategory(Short category) {
        this.category = category;
    }

    public String getBookCoverImg() {
        return bookCoverImg;
    }

    public void setBookCoverImg(String bookCoverImg) {
        this.bookCoverImg = bookCoverImg;
    }

    @Override
    public String toString() {
        return "UploadBooksDO{" +
                "bookId=" + bookId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", onLoan=" + onLoan +
                ", comment='" + comment + '\'' +
                ", bookName='" + bookName + '\'' +
                ", wantedNum=" + wantedNum +
                ", category=" + category +
                ", createdTime=" + createdTime +
                ", bookCoverImg='" + bookCoverImg + '\'' +
                '}';
    }
}
