package ief.utils;

/**
 * Created by zhangdongsheng on 15/7/25.
 */
public class BaiduAddressUtil {
    public static final String BAIDU_GET_DIS= "http://api.map.baidu.com/geocoder/v2/?ak=F7f4e9ffd4170d51d05ffe0e69478e3e&callback=renderReverse&output=json&pois=1&location=";

    public static String getCityAndDistrict(String lon, String lat){
        try {
            String text = HttpClientUtil.executeGet(BAIDU_GET_DIS + lat + "," + lon);
            System.out.println(text);
            return getCityAndDistrict(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getCityAndDistrict(String text){
        return getCity(text) + " " + getDistrict(text);
    }

    public static String getCity(String text) {
        return subStr(text, "city", 7);
    }

    public static String getDistrict(String text) {
        return subStr(text, "district", 11);
    }
    
    public static String getStreet(String text) {
        return subStr(text, "street", 9);
    }

    private static String subStr(String sourceStr, String targetStr, int toSize) {
        return subStr(sourceStr, 0, sourceStr.length(),
                targetStr, 0, targetStr.length(), 0, toSize);
    }

    private static String subStr(String sourceStr, int sourceOffset, int sourceCount,
                                 String targetStr, int targetOffset, int targetCount,
                                 int fromIndex, int toSize) {
        char[] source = sourceStr.toCharArray();
        char[] target = targetStr.toCharArray();
        if (fromIndex >= sourceCount) {
            return null;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return null;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++)
                    ;

                if (j == end) {
                    /* Found whole string. */
                    return cutStr(source, i - sourceOffset + toSize);
                }
            }
        }
        return null;
    }

    private static String cutStr(char[] source, int i) {
        String msg = "";
        for (; i < source.length; i++) {
            if (source[i] == '"') {
                return msg;
            } else {
                msg += source[i];
            }

        }
        return null;
    }


    public static void main(String[] args) {
//        String text = "renderReverse&&renderReverse({\"status\":0,\"result\":{\"location\":{\"lng\":116.34980298059,\"lat\":40.044685049282},\"formatted_address\":\"北京市海淀区小营东路6号\",\"business\":\"小营,清河,西三旗\",\"addressComponent\":{\"city\":\"北京市\",\"country\":\"中国\",\"direction\":\"附近\",\"distance\":\"6\",\"district\":\"海淀区\",\"province\":\"北京市\",\"street\":\"小营东路\",\"street_number\":\"6号\",\"country_code\":0},\"pois\":[{\"addr\":\"小营环岛东南角\",\"cp\":\"NavInfo\",\"direction\":\"附近\",\"distance\":\"29\",\"name\":\"通厦小营建材城\",\"poiType\":\"购物\",\"point\":{\"x\":116.3495357347,\"y\":40.044700238915},\"tag\":\"购物;家居建材\",\"tel\":\"\",\"uid\":\"bf29b7025879688009b93a7b\",\"zip\":\"\"},{\"addr\":\"北京市海淀区小营桥东\",\"cp\":\"NavInfo\",\"direction\":\"东南\",\"distance\":\"69\",\"name\":\"火车票飞机票代售处\",\"poiType\":\"生活服务\",\"point\":{\"x\":116.34943692109,\"y\":40.045073074286},\"tag\":\"生活服务;售票处\",\"tel\":\"\",\"uid\":\"915f6bccd0306e54bfa8220d\",\"zip\":\"\"},{\"addr\":\"北京市海淀区东升乡\",\"cp\":\"NavInfo\",\"direction\":\"东\",\"distance\":\"91\",\"name\":\"小营环岛\",\"poiType\":\"行政地标\",\"point\":{\"x\":116.34898776833,\"y\":40.044776187027},\"tag\":\"行政地标;村庄\",\"tel\":\"\",\"uid\":\"be559eccca1cd30dc41e5101\",\"zip\":\"\"},{\"addr\":\"海淀区清河小营东路12号(近国电宾馆)\",\"cp\":\"NavInfo\",\"direction\":\"西\",\"distance\":\"370\",\"name\":\"北京信息科技大学清河小营校区\",\"poiType\":\"教育培训\",\"point\":{\"x\":116.35307505841,\"y\":40.044230739598},\"tag\":\"教育培训;高等院校\",\"tel\":\"\",\"uid\":\"b7021bf80df4eb84f4e73bce\",\"zip\":\"\"},{\"addr\":\"海淀区昌平路南段36号旁门(近京藏高速)\",\"cp\":\"NavInfo\",\"direction\":\"北\",\"distance\":\"302\",\"name\":\"北京大学人民医院(清河分院)\",\"poiType\":\"医疗\",\"point\":{\"x\":116.35048793854,\"y\":40.04266341681},\"tag\":\"医疗;综合医院\",\"tel\":\"\",\"uid\":\"2e9dcd3012069f83213bf2c9\",\"zip\":\"\"},{\"addr\":\"海淀清河小营环岛东侧150米\",\"cp\":\"NavInfo\",\"direction\":\"西南\",\"distance\":\"185\",\"name\":\"美欣家园\",\"poiType\":\"房地产\",\"point\":{\"x\":116.35110776934,\"y\":40.045480429101},\"tag\":\"房地产;住宅区\",\"tel\":\"\",\"uid\":\"691305235a784b08735e80ab\",\"zip\":\"\"},{\"addr\":\"北京市海淀区清河小营甲1号\",\"cp\":\"NavInfo\",\"direction\":\"东南\",\"distance\":\"155\",\"name\":\"六三九二六部队招待所\",\"poiType\":\"酒店\",\"point\":{\"x\":116.34856556475,\"y\":40.04518354363},\"tag\":\"酒店;其他\",\"tel\":\"\",\"uid\":\"2eb1530cc6548c60ad080fa1\",\"zip\":\"\"},{\"addr\":\"小营东路12号北京信息科技大学清河小营校区\",\"cp\":\"NavInfo\",\"direction\":\"西\",\"distance\":\"211\",\"name\":\"北京信息科技大学(清河小营校区)-第二教学楼\",\"poiType\":\"教育培训\",\"point\":{\"x\":116.35169166792,\"y\":40.044562151219},\"tag\":\"教育培训;其他\",\"tel\":\"\",\"uid\":\"d5c4ce5e0b647f6e72455bee\",\"zip\":\"\"},{\"addr\":\"小营东路12号北京信息科技大学清河小营校区内\",\"cp\":\"NavInfo\",\"direction\":\"西\",\"distance\":\"278\",\"name\":\"北京信息科技大学清河小营校区-门诊部北楼\",\"poiType\":\"医疗\",\"point\":{\"x\":116.35214980373,\"y\":40.044016702063},\"tag\":\"医疗;诊所\",\"tel\":\"\",\"uid\":\"04bdf4a318d8c24f354aae70\",\"zip\":\"\"},{\"addr\":\"北京市西三旗花园三里75号\",\"cp\":\"NavInfo\",\"direction\":\"东南\",\"distance\":\"387\",\"name\":\"北京市公安局海淀分局西三旗派出所\",\"poiType\":\"政府机构\",\"point\":{\"x\":116.3476133609,\"y\":40.046764616357},\"tag\":\"政府机构;公检法机构\",\"tel\":\"\",\"uid\":\"476392407fa005e14d776e4f\",\"zip\":\"\"}],\"poiRegions\":[],\"sematic_description\":\"通厦小营建材城附近29米\",\"cityCode\":131}})";
//        System.out.println(getCityAndDistrict(text));,116.23
        System.out.println("###" + BaiduAddressUtil.getCityAndDistrict("39.54", "116.23"));
        //11
        //7
    }
}
