package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class KnownPersonsDO {
    private Long id;
    private Long userId;
    private Long keyUserId;
    private Date createdTime;

    public Long getKeyUserId() {
        return keyUserId;
    }

    public void setKeyUserId(Long keyUserId) {
        this.keyUserId = keyUserId;
    }

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
        return "KnownPersonsDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", keyUserId=" + keyUserId +
                ", createdTime=" + createdTime +
                '}';
    }
}
