package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class BooksDO {
    private Long id;
    private String bookName;
    private Integer categoryId;
    private Date createdTime;


    @Override
    public String toString() {
        return "BooksDO{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", categoryId=" + categoryId +
                ", createdTime=" + createdTime +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }
}
