package ief.service;

import ief.persistence.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangdongsheng on 15/7/21.
 */
@Service
public class VerifyService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public boolean verifyPhone(String phone){
        int rtl = userInfoMapper.getNumByPhone(phone);
        if(rtl != 0) return false;
        else return true;
    }
}
