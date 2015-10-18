package ief.utils;

import ief.dto.params.BaseParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangdongsheng on 15/7/4.
 */
public class SessionUtil {
    private static final Map<Long, String> sessionMap = new HashMap<>();

    public static boolean validSessionId(BaseParam baseParam){
        String sessionId = sessionMap.get(baseParam.getUserId());
        if(sessionId.equals(baseParam.getSessionId()))
            return true;
        else return false;
    }

    public static String getSessionId(Long userId) {
        String sessionId = sessionMap.get(userId);
        if (StringUtils.isEmpty(sessionId)) {
            sessionId = userId + "-" + System.currentTimeMillis();
            sessionMap.put(userId, sessionId);
        }
        return sessionId;
    }

    public static String newSessionId(Long userId) {
        String sessionId = userId + "-" + System.currentTimeMillis();
        sessionMap.put(userId, sessionId);
        return sessionId;
    }

    public static String removeSessionId(Long userId) {
        return sessionMap.remove(userId);

    }

    public static void main(String[] args) {
        Long userId = 123029912L;
        System.out.println(userId + "-" + System.currentTimeMillis());
    }
}
