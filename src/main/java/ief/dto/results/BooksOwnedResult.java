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
    private int category;
    private String categoryName;
    private String defaultPlace;

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
	public String getDefaultPlace() {
		return defaultPlace;
	}
	public void setDefaultPlace(String defaultPlace) {
		this.defaultPlace = defaultPlace;
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
                '}';
    }
}
