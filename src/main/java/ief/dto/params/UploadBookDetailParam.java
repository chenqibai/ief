package ief.dto.params;

/**
 * Created by zhangdongsheng on 15/7/12.
 */
public class UploadBookDetailParam {
    private String uploadBookId;

    public String getUploadBookId() {
        return uploadBookId;
    }

    public void setUploadBookId(String uploadBookId) {
        this.uploadBookId = uploadBookId;
    }

    @Override
    public String toString() {
        return "UploadBookDetailParam{" +
                "uploadBookId='" + uploadBookId + '\'' +
                '}';
    }
}

