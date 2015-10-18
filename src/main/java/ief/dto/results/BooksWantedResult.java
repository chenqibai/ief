package ief.dto.results;

/**
 * Created by zhangdongsheng on 15/7/12.
 */
public class BooksWantedResult {
    private Long bookId;
    private String bookCoverImg;
    private String bookName;
    private String comment;
    private String userHeadImg;
    private String userName;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "BooksWantedResult{" +
                "bookId=" + bookId +
                ", bookCoverImg='" + bookCoverImg + '\'' +
                ", bookName='" + bookName + '\'' +
                ", comment='" + comment + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
