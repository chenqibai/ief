package ief.dto.results;

public class BookDetailResult {
	private String bookName;
    private String comment;
    private String userName;
    private Integer wantedNum;
    private String bookCoverImg;
    private String userHeadImg;
    private String bookId;
    private String userId;
    private int category;
    private String categoryName;
    private String district;
    private String street;
    private String constellation;
    private String sex;
    private String city;
    private String defaultPlace;
    private Integer ownedNum;
    
	public Integer getOwnedNum() {
		return ownedNum;
	}
	public void setOwnedNum(Integer ownedNum) {
		this.ownedNum = ownedNum;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getWantedNum() {
		return wantedNum;
	}
	public void setWantedNum(Integer wantedNum) {
		this.wantedNum = wantedNum;
	}
	public String getBookCoverImg() {
		return bookCoverImg;
	}
	public void setBookCoverImg(String bookCoverImg) {
		this.bookCoverImg = bookCoverImg;
	}
	public String getUserHeadImg() {
		return userHeadImg;
	}
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
}
