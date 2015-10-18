package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class UploadBooksDO {
    private Long id;
    private Long bookId;
    private Long userId;
    private Short onLoan;
    private String comment;
    private String bookName;
    private Long wantedNum;
    private Short category;
    private Date createdTime;
    private String bookCoverImg;

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
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