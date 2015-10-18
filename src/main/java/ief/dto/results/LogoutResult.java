package ief.dto.results;

/**
 * Created by zhangdongsheng on 15/7/5.
 */
public class LogoutResult {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "LogoutResult{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
