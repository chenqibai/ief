package ief.dto.results;

import ief.enums.CategoryEnum;

/**
 * Created by zhangdongsheng on 15/7/12.
 */
public class BooksOwnedResult {
    private Long bookId;
    private String bookCoverImg;
    private String bookName;
    private String comment;
    private int wantedNum;
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

    public Long getBookId() {
        return bookId;
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

    public int getWantedNum() {
        return wantedNum;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public void setWantedNum(int wantedNum) {
        this.wantedNum = wantedNum;
    }

    @Override
    public String toString() {
        return "BooksOwnedResult{" +
                "bookId=" + bookId +
                ", bookCoverImg='" + bookCoverImg + '\'' +
                ", bookName='" + bookName + '\'' +
                ", comment='" + comment + '\'' +
                ", wantedNum=" + wantedNum +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
