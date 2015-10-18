package ief.dto.results;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class AddImageResult {
    private String imgAddress;

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    @Override
    public String toString() {
        return "AddImageResult{" +
                "imgAddress='" + imgAddress + '\'' +
                '}';
    }
}
