package ief.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ief.constants.Config;
import ief.domain.UserInfoDO;
import ief.dto.params.BaseParam;
import ief.dto.results.BaseResult;
import ief.dto.results.LoginResult;
import ief.dto.results.LogoutResult;
import ief.dto.results.RegisterUserResult;
import ief.dto.results.UserInfoResult;
import ief.enums.StatusEnum;
import ief.persistence.UserInfoMapper;
import ief.service.UserService;
import ief.utils.ControllerUtil;
import ief.utils.HttpUtil;
import ief.utils.JsonUtil;

/**
 * Created by zhangdongsheng on 2015/4/5.
 */
@Controller
public class UserInfoController {
    private final Log logger = LogFactory.getLog(UserInfoController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public
    @ResponseBody
    String hello() {
//    	List<UserInfoDO> list=userInfoMapper.getUserInfo();
//    	for(UserInfoDO userInfoDO:list){
//    		userInfoDO.setPhone(SecretUtil.encrypt(userInfoDO.getPhone()));
//    		userInfoDO.setPassword(SecretUtil.encrypt(userInfoDO.getPassword()));
//    		userInfoMapper.updatePassword(userInfoDO);
//    	}
        System.out.println(Config.getInstance().getProperty("IMG_ADDRESS"));
        return "ok";
    }

    @RequestMapping(value = "/app/register")
    public void register(
            BaseParam baseParam,
            UserInfoDO registerUserParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;
        try {
            LoginResult loginResult = new LoginResult();
            int rtl = userService.register(baseParam, registerUserParam, loginResult);
            if (rtl == -1) {
                baseResult = new BaseResult(1, "该手机号已经注册");
            } else if (rtl == 1) {
                baseResult = new BaseResult(StatusEnum.SUCCESS, loginResult);
            }
        } catch (Exception e) {
            baseResult = new BaseResult(StatusEnum.SERVER_ERR);
            logger.error(e.getMessage(), e);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/register_detail")
    public void register_user(
            BaseParam baseParam,
            UserInfoDO registerDetailUserParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;

        try {
            RegisterUserResult registerUserResult = new RegisterUserResult();
            int rtl = userService.registerDetail(baseParam, registerDetailUserParam, registerUserResult);
            if (rtl == 0) {
                baseResult = new BaseResult(StatusEnum.FAILED_NOT_EXIST);
            } else if (rtl == 1) {
                baseResult = new BaseResult(StatusEnum.SUCCESS, registerUserResult);
            }
        } catch (Exception e) {
            baseResult = new BaseResult(StatusEnum.SERVER_ERR);
            logger.error(e.getMessage(), e);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/login")
    public void login(
            BaseParam baseParam,
            UserInfoDO loginParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;
        LoginResult loginResult = new LoginResult();
        try {
            int rtl = userService.login(baseParam, loginParam, loginResult);
            if (rtl == 1) {
                LinkedList<LoginResult> list = new LinkedList<LoginResult>();
                list.add(loginResult);
                baseResult = new BaseResult(StatusEnum.SUCCESS, list);
            } else if(rtl == 2){
                baseResult = new BaseResult(1, "sorry，密码不正确");
            } else if (rtl == 0) {
                baseResult = new BaseResult(1, "用户不存在");
            }
        } catch (Exception e) {
            baseResult = new BaseResult(StatusEnum.FAILED);
            logger.error(e.getMessage(), e);
        }
        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/logout", method = RequestMethod.POST)
    public void logout(
            BaseParam baseParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;
        try {
            LogoutResult logoutResult = userService.logout(baseParam);
            LinkedList<LogoutResult> list = new LinkedList<LogoutResult>();
            list.add(logoutResult);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED, null);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/get_user_info")
    public void get_user_info(
            BaseParam baseParam,
            Long queryUserId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        HttpUtil.logRequest(logger, "get_user_info", httpServletRequest);
        BaseResult baseResult = null;
        try {
        	boolean flag=queryUserId!=null;
            UserInfoResult userInfoResult = userService.getUserInfo(baseParam,flag?queryUserId:baseParam.getUserId(),flag);
            //TODO 是查看自己，以后有没有查看他人？queryUserId??
            userInfoResult.setUserId(flag?queryUserId:baseParam.getUserId());
            baseResult = new BaseResult(StatusEnum.SUCCESS, userInfoResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED, null);
        }
        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/update_user_info", method = RequestMethod.POST)
    public void update_user_info(
            BaseParam baseParam,
            UserInfoDO updateUserInfoParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        HttpUtil.logRequest(logger, "update_user_info", httpServletRequest);
        BaseResult baseResult = null;
        try {
            updateUserInfoParam.setUserId(baseParam.getUserId());
            int rtl = userService.updateUserInfo(updateUserInfoParam,baseParam);
            baseResult = new BaseResult(StatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }
    
}
