package ief.dto.results;

import ief.enums.CategoryEnum;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class ListBooksResult {
    private String bookName;
    private String comment;
    private String userName;
    private String locate;
    private Integer wantedNum;
    private String bookCoverImg;
    private String userHeadImg;
    private String uploadBookId;
    private String userID;
    private int categoryId;
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        this.categoryName = CategoryEnum.CATEGORY_MAP.get(categoryId);
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public void setLocate(String locate) {
        this.locate = locate;
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

    public String getLocate() {
        return locate;
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

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setBookCoverImg(String bookCoverImg) {
        this.bookCoverImg = bookCoverImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUploadBookId() {
        return uploadBookId;
    }

    public void setUploadBookId(String uploadBookId) {
        this.uploadBookId = uploadBookId;
    }

    @Override
    public String toString() {
        return "ListBooksResult{" +
                "bookName='" + bookName + '\'' +
                ", comment='" + comment + '\'' +
                ", userName='" + userName + '\'' +
                ", locate='" + locate + '\'' +
                ", wantedNum=" + wantedNum +
                ", bookCoverImg='" + bookCoverImg + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", uploadBookId='" + uploadBookId + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
