package ief.dto.results;

import ief.model.BookAbstractDO;

import java.util.List;

/**
 * Created by zhangdongsheng on 15/7/12.
 */
public class UploadBookDetailResult {
    private String bookCoverImg;
    private String bookName;
    private String comment;
    private String userHeadImg;
    private String userName;
    private String locate;
    private Integer ownedNum;
    private Integer wantedNum;
    private Long userId;
    /**
     * 简介
     */
    private List<BookAbstractDO> bookAbstractDOList;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBookCoverImg() {
        return bookCoverImg;
    }

    public String getBookName() {
        return bookName;
    }

    public String getComment() {
        return comment;
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

    public Integer getOwnedNum() {
        return ownedNum;
    }

    public Integer getWantedNum() {
        return wantedNum;
    }

    public void setBookCoverImg(String bookCoverImg) {
        this.bookCoverImg = bookCoverImg;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public void setOwnedNum(Integer ownedNum) {
        this.ownedNum = ownedNum;
    }

    public void setWantedNum(Integer wantedNum) {
        this.wantedNum = wantedNum;
    }

    public List<BookAbstractDO> getBookAbstractDOList() {
        return bookAbstractDOList;
    }

    public void setBookAbstractDOList(List<BookAbstractDO> bookAbstractDOList) {
        this.bookAbstractDOList = bookAbstractDOList;
    }

    @Override
    public String toString() {
        return "UploadBookDetailResult{" +
                "bookCoverImg='" + bookCoverImg + '\'' +
                ", bookName='" + bookName + '\'' +
                ", comment='" + comment + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", userName='" + userName + '\'' +
                ", locate='" + locate + '\'' +
                ", ownedNum=" + ownedNum +
                ", wantedNum=" + wantedNum +
                ", userId=" + userId +
                ", bookAbstractDOList=" + bookAbstractDOList +
                '}';
    }
}

