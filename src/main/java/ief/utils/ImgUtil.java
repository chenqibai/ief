package ief.utils;

import ief.constants.Config;
import ief.dto.params.BaseParam;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhangdongsheng on 15/6/27.
 */
public class ImgUtil {

    public static String getFullPath(BaseParam baseParam, String path){
        if(baseParam.getUserId() == null || baseParam.getUserId().equals(0) || StringUtils.isEmpty(path))
            throw new RuntimeException("user_id或path不能为空");
        return Config.getInstance().getProperty("IMG_ADDRESS") + baseParam + path;
    }

    public static String generateFullPath(String endPath){
        return Config.getInstance().getProperty("IMG_ADDRESS") + endPath;
    }

}
