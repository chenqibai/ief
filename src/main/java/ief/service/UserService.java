package ief.service;

import ief.dto.params.*;
import ief.dto.results.LoginResult;
import ief.dto.results.LogoutResult;
import ief.dto.results.RegisterUserResult;
import ief.dto.results.UserInfoResult;
import ief.persistence.BooksOwnedMapper;
import ief.persistence.BooksWantedMapper;
import ief.persistence.UserInfoMapper;
import ief.domain.UserInfoDO;
import ief.utils.SessionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
@Service
public class UserService {
    private final Log logger = LogFactory.getLog(UserService.class);
//    @Autowired
//    private IUserInfoDao userInfoDaoImpl;
//
//    public List<UserInfoEntity> getUserInfos(){
//        List<UserInfoEntity> list = userInfoDaoImpl.getUserInfoList();
//        logger.info("zds" + list.toString());
//        return null;
//    }

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BooksWantedMapper booksWantedMapper;
    @Autowired
    private BooksOwnedMapper booksOwnedMapper;

    public List<UserInfoDO> getUserInfos() {
        return this.userInfoMapper.getUserInfo();
    }


    public int register(BaseParam baseParam, RegisterUserParam registerUserParam, LoginResult loginResult) {
        Integer cu = userInfoMapper.countUserInfoByPhone(registerUserParam.getPhone());
        if (cu == 0) {
            int rtl = userInfoMapper.addUserInfo(registerUserParam);
            logger.info(rtl);
            if (rtl == 1) {
                loginResult.setSessionId(SessionUtil.newSessionId(registerUserParam.getId()));
                loginResult.setUserId(registerUserParam.getId());
                loginResult.setWanted(0);
                return 1;
            } else
                throw new RuntimeException("添加用户失败,rt不唯一");
        } else {
            return -1;
        }
    }

    public int registerDetail(BaseParam baseParam, RegisterDetailUserParam registerDetailUserParam, RegisterUserResult registerUserResult) {
        Integer cu = userInfoMapper.countUserInfoById(baseParam.getUserId());
        if (cu == 1) {
            registerDetailUserParam.setUserIdParam(baseParam.getUserId());
            userInfoMapper.addUserInfoDetail(registerDetailUserParam);
            registerUserResult.setSessionId(SessionUtil.getSessionId(baseParam.getUserId()));
            return 1;
        } else if (cu == 0) {
            return 0;
        } else {
            return 2;
        }
    }

    public int login(BaseParam baseParam, LoginParam loginParam, LoginResult loginResult) {
        List<UserInfoDO> list = userInfoMapper.getUserInfoByPhoneAndPassword(loginParam.getPhone());
        if (list.size() == 1) {
            logger.info(list);
            UserInfoDO userInfoDO = list.get(0);
            if(!userInfoDO.getPassword().equals(loginParam.getPassword())) return 3;
            loginResult.setUserId(userInfoDO.getId());
            loginResult.setWanted(booksWantedMapper.getBooksWantedNum(userInfoDO.getId()));
            loginResult.setOwned(booksOwnedMapper.getBooksOwnedNum(userInfoDO.getId()));
            loginResult.setUserName(userInfoDO.getUserName());
            loginResult.setSex(userInfoDO.getSex());
            loginResult.setBirthday(userInfoDO.getBirthday());
            loginResult.setBirthdayType(userInfoDO.getBirthdayType());
            loginResult.setHometown(userInfoDO.getHometown());
            loginResult.setLocate(userInfoDO.getLocate());
            loginResult.setUserHeadImg(userInfoDO.getUserHeadImg());
            loginResult.setSessionId(SessionUtil.newSessionId(userInfoDO.getId()));
            return 1;
        } else if (list.size() == 0) {
            return 0;
        } else {
            return 2;
        }
    }

    public LogoutResult logout(BaseParam baseParam) {
        LogoutResult logoutResult = new LogoutResult();
        logoutResult.setSessionId(SessionUtil.removeSessionId(baseParam.getUserId()));
        return logoutResult;
    }

    public UserInfoResult getUserInfo(Long userId){
        UserInfoResult userInfoResult = userInfoMapper.getUserInfoByUserId(userId);
        logger.info("###" + userInfoResult);
        int wantedNum = booksWantedMapper.getBooksWantedNum(userId);
        int ownedNum = booksOwnedMapper.getBooksOwnedNum(userId);
        logger.info("###" + userInfoResult);
        userInfoResult.setWantedNum(wantedNum);
        userInfoResult.setOwnedNum(ownedNum);
        logger.info("###" + userInfoResult);
        return userInfoResult;
    }

    public int updateUserInfo(UpdateUserInfoParam updateUserInfoParam){
        return userInfoMapper.updateUserInfo(updateUserInfoParam);
    }

}
