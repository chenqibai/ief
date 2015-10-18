package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class ConcernedPersonsDO {
    private Long id;
    private Long userId;
    private Long keyUserId;
    private Date createdTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {

        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getKeyUserId() {
        return keyUserId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setKeyUserId(Long keyUserId) {
        this.keyUserId = keyUserId;
    }

    @Override
    public String toString() {
        return "ConcernedPersonsDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", keyUserId=" + keyUserId +
                ", createdTime=" + createdTime +
                '}';
    }
}
