package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/7/5.
 */
public class BooksOwnedDO {
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

    public Long getKeyUserId() {
        return keyUserId;
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

    public void setKeyUserId(Long keyUserId) {
        this.keyUserId = keyUserId;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
