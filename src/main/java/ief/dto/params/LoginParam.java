package ief.dto.params;

/**
 * Created by zhangdongsheng on 15/7/4.
 */
public class LoginParam {
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginParam{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
