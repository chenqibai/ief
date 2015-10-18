package ief.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class ControllerUtil {
    public static void responseWriter(HttpServletResponse httpServletResponse, String responseMsg){
        PrintWriter printWriter = null;
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setCharacterEncoding("UTF-8");
        try {
            printWriter = httpServletResponse.getWriter();
            printWriter.println(responseMsg);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
