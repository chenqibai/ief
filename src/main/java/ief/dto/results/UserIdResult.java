package ief.dto.results;

/**
 * Created by zhangdongsheng on 15/7/31.
 */
public class UserIdResult {
    private Long userId;

    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserIdResult{" +
                "userId=" + userId +
                '}';
    }
}
