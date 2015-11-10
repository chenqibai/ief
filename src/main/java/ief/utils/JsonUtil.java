package ief.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 * Json util class.
 * 
 * @author zxwu
 */
public final class JsonUtil {
    private static Log logger = LogFactory.getLog(JsonUtil.class);
    private static ObjectMapper mapper = null;

    /**
     * Constructor
     */
    private JsonUtil() {
    }

    private static ObjectMapper getObjectMapper() {
        if (mapper == null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper = new ObjectMapper();
            SerializationConfig sc = mapper.getSerializationConfig()
                    .withSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);		// 忽略值为null的成员

    		mapper.setSerializationConfig(sc);
            mapper.setDateFormat(df);
        }
        return mapper;
    }

    /**
     * From json string to bean.
     * 
     * @param <T> bean class type.
     * @param json json string.
     * @param clazz bean class.
     * @return the bean instance.
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        if (!StringUtils.isEmpty(json)) {
            try {
                return getObjectMapper().readValue(json, clazz);
            } catch (Exception e) {
                logger.error("JSONString : " + json, e);
            }
        }
        return null;
    }

    /**
     * From json string to bean list.
     * 
     * @param <T> <T>
     * @param json json
     * @param clazz clazz
     * @return ArrayList<T>
     */
    public static <T> ArrayList<T> toBeanList(String json, Class<T> clazz) {
        if (!StringUtils.isEmpty(json)) {
            ObjectMapper mapperin = getObjectMapper();
            try {
                return mapperin.readValue(json, TypeFactory.collectionType(ArrayList.class, clazz));
            } catch (Exception e) {
                logger.error("JSONString : " + json, e);
            }
        }
        return null;
    }

    /**
     * To json string.
     * 
     * @param object the object.
     * @return json string.
     */
    public static String toString(Object object) {
        return toString(object, getObjectMapper());
    }

    /**
     * To json string base on given mapper.
     * 
     * @param object object
     * @param mapper mapper
     * @return json string.
     */
    public static String toString(Object object, ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error(e, e);
            return "";
        }
    }
}
