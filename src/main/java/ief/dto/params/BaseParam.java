package ief.dto.params;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
public class BaseParam {
    private String version;
    private Long userId;
    private String source;
    private String deviceId;
    private String sessionId;
    /**
     * 经度
     */
    private String lon;
    /**
     * 纬度
     */
    private String lat;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public String getSource() {
        return source;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "BaseParam{" +
                "version='" + version + '\'' +
                ", userId=" + userId +
                ", source='" + source + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
