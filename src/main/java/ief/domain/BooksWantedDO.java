package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
public class BooksWantedDO {
    private Long id;
    private Long bookId;
    private Long keyUserId;
    private Date createdTime;

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
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

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getKeyUserId() {
        return keyUserId;
    }

    public void setKeyUserId(Long keyUserId) {
        this.keyUserId = keyUserId;
    }

    @Override
    public String toString() {
        return "BooksWantedDO{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", keyUserId=" + keyUserId +
                ", createdTime=" + createdTime +
                '}';
    }
}
