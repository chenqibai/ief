package ief.dto.results;

import java.math.BigDecimal;

import ief.enums.CategoryEnum;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class ListBooksResult {
    private String bookName;
    private String comment;
    private String userName;
    private String bookCoverImg;
    private String userHeadImg;
    private String bookId;
    private String userId;
    private String district;
    private Short category;
    private String street;
    private String constellation;
    private String city;
    private BigDecimal lon;//经度
    private BigDecimal lat;//纬度
    private Integer wantedNum;
    
	public Short getCategory() {
		return category;
	}
	public void setCategory(Short category) {
		this.category = category;
	}
	public Integer getWantedNum() {
		return wantedNum;
	}
	public void setWantedNum(Integer wantedNum) {
		this.wantedNum = wantedNum;
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
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getBookName() {
        return bookName;
    }

    public String getComment() {
        return comment;
    }
    public String getUserName() {
        return userName;
    }
    public String getBookCoverImg() {
        return bookCoverImg;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setBookCoverImg(String bookCoverImg) {
        this.bookCoverImg = bookCoverImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }
    @Override
    public String toString() {
        return "ListBooksResult{" +
                "bookName='" + bookName + '\'' +
                ", comment='" + comment + '\'' +
                ", userName='" + userName + '\'' +
                ", bookCoverImg='" + bookCoverImg + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                '}';
    }
}
