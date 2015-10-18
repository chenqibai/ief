package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class BookUserWanted {
    private Long id;
    private Long userId;
    private Date createdTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {

        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return "BookUserWanted{" +
                "id=" + id +
                ", userId=" + userId +
                ", createdTime=" + createdTime +
                '}';
    }
}
