package ief.dto.results;

import java.util.Date;

/**
 * Created by zhangdongsheng on 15/7/4.
 */
public class RegisterUserResult {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "RegisterUserResult{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
