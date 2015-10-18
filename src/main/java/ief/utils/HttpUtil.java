package ief.utils;

import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by zhangdongsheng on 15/6/27.
 */
public class HttpUtil {
    public static String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    public static void logRequest(Log logger, String requestName, HttpServletRequest request) {
        Enumeration e = request.getParameterNames();
        StringBuilder sb = new StringBuilder();
        while (e.hasMoreElements()) {
            String name = (String)e.nextElement();
            sb.append(name + ": " + request.getParameter(name) + " ");
        }
        logger.info(request.toString() + " " + requestName +": "+ sb.toString());
    }
}
