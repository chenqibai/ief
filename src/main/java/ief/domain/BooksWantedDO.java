package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
public class BooksWantedDO {
    private Long bookId;
    private Long userId;
    private Date createdTime;
    
    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBookId() {
        return bookId;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    @Override
    public String toString() {
        return "BooksWantedDO{" +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", createdTime=" + createdTime +
                '}';
    }
}
