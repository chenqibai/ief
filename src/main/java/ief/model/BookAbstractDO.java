package ief.model;

/**
 * Created by zhangdongsheng on 15/7/12.
 */
public class BookAbstractDO {
    private String userId;
    private String userName;
    private String userHeadImg;
    private String comment;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public String getComment() {
        return comment;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BookAbstractDO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
