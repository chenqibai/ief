package ief.dto.params;

/**
 * Created by zhangdongsheng on 15/6/25.
 * 增添图片
 */
public class AddBookParam {
    private String bookName;
    private String comment;
    private Short category;
    private String bookCoverImg;

    public String getBookName() {
        return bookName;
    }

    public String getComment() {
        return comment;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getBookCoverImg() {
        return bookCoverImg;
    }

    public void setBookCoverImg(String bookCoverImg) {
        this.bookCoverImg = bookCoverImg;
    }

    public Short getCategory() {
        return category;
    }

    public void setCategory(Short category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AddBookParam{" +
                "bookName='" + bookName + '\'' +
                ", comment='" + comment + '\'' +
                ", category='" + category + '\'' +
                ", bookCoverImg='" + bookCoverImg + '\'' +
                '}';
    }
}
