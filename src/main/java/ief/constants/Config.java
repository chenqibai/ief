package ief.constants;

import ief.enums.CategoryEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhangdongsheng on 15/7/10.
 * 系统本地静态信息
 */
public class Config {
    private final Log logger = LogFactory.getLog(Config.class);

    private static Config CONFIG = new Config();
    private Properties prop;

    private Config() {
        InputStream inputStream = null;
        try {
            prop = new Properties();
            String propFileName = "runtimecfg/config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Config getInstance(){
        return CONFIG;
    }
    public String getProperty(String key){
        return prop.getProperty(key);
    }

}
