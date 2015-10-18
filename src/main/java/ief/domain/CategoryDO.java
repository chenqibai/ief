package ief.domain;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class CategoryDO {
    private Long id;
    private String categoryName;
    private Date createdTime;

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "CategoryDO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
