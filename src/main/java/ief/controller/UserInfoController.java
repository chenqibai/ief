package ief.controller;

import com.alibaba.fastjson.JSON;
import ief.constants.Config;
import ief.domain.UserInfoDO;
import ief.dto.params.*;
import ief.dto.results.*;
import ief.enums.StatusEnum;
import ief.service.UserService;
import ief.utils.ControllerUtil;
import ief.utils.HttpUtil;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

/**
 * Created by zhangdongsheng on 2015/4/5.
 */
@Controller
public class UserInfoController {
    private final Log logger = LogFactory.getLog(UserInfoController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public
    @ResponseBody
    String hello() {
        System.out.println(Config.getInstance().getProperty("IMG_ADDRESS"));
        return "ok";
    }

    @RequestMapping(value = "/app/register", method = RequestMethod.POST)
    public void register(
            BaseParam baseParam,
            RegisterUserParam registerUserParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;
        try {
            LoginResult loginResult = new LoginResult();
            int rtl = userService.register(baseParam, registerUserParam, loginResult);
            logger.info(rtl);
            if (rtl == -1) {
                baseResult = new BaseResult(1, "该手机号已经注册");
            } else if (rtl == 1) {
                LinkedList<LoginResult> list = new LinkedList();
                list.add(loginResult);
                baseResult = new BaseResult(StatusEnum.SUCCESS, list);
            } else {
                baseResult = new BaseResult(StatusEnum.FAILED);
            }
        } catch (Exception e) {
            baseResult = new BaseResult(StatusEnum.FAILED);
            logger.error(e.getMessage(), e);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/register_detail", method = RequestMethod.POST)
    public void register_user(
            BaseParam baseParam,
            RegisterDetailUserParam registerDetailUserParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;

        try {
            RegisterUserResult registerUserResult = new RegisterUserResult();
            int rtl = userService.registerDetail(baseParam, registerDetailUserParam, registerUserResult);
            if (rtl == -1) {
                baseResult = new BaseResult(1, "用户已存在");
            } else if (rtl == 1) {
                LinkedList<RegisterUserResult> list = new LinkedList();
                list.add(registerUserResult);
                baseResult = new BaseResult(StatusEnum.SUCCESS, list);
            } else {
                baseResult = new BaseResult(StatusEnum.FAILED);
            }
        } catch (Exception e) {
            baseResult = new BaseResult(StatusEnum.FAILED);
            logger.error(e.getMessage(), e);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/login", method = RequestMethod.POST)
    public void login(
            BaseParam baseParam,
            LoginParam loginParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;
        LoginResult loginResult = new LoginResult();
        try {
            int rtl = userService.login(baseParam, loginParam, loginResult);
            if (rtl == 1) {
                LinkedList<LoginResult> list = new LinkedList();
                list.add(loginResult);
                baseResult = new BaseResult(StatusEnum.SUCCESS, list);
            } else if(rtl == 3){
                baseResult = new BaseResult(1, "sorry，密码不正确");
            } else if (rtl == 0) {
                baseResult = new BaseResult(1, "用户不存在");
            } else if (rtl == 2) {
                baseResult = new BaseResult(1, "多个用户");
                logger.error("多个用户：" + baseParam.toString() + loginParam.toString());
            } else {
                baseResult = new BaseResult(StatusEnum.FAILED);
                logger.error("多个用户：" + baseParam.toString() + loginParam.toString());
            }
        } catch (Exception e) {
            baseResult = new BaseResult(StatusEnum.FAILED);
            logger.error(e.getMessage(), e);
        }
        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
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
            LinkedList<LogoutResult> list = new LinkedList();
            list.add(logoutResult);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED, null);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/get_user_info", method = RequestMethod.POST)
    public void get_user_info(
            BaseParam baseParam,
            Long queryUserId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        HttpUtil.logRequest(logger, "get_user_info", httpServletRequest);
        BaseResult baseResult = null;
        try {
            UserInfoResult userInfoResult = userService.getUserInfo(queryUserId);
            userInfoResult.setUserId(queryUserId);
            userInfoResult.setBirthdayStr();
            LinkedList<UserInfoResult> list = new LinkedList<>();
            list.add(userInfoResult);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED, null);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/update_user_info", method = RequestMethod.POST)
    public void update_user_info(
            BaseParam baseParam,
            UpdateUserInfoParam updateUserInfoParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        HttpUtil.logRequest(logger, "update_user_info", httpServletRequest);
        BaseResult baseResult = null;
        try {
            updateUserInfoParam.setId(baseParam.getUserId());
            int rtl = userService.updateUserInfo(updateUserInfoParam);
            logger.info("update_user_info:"+ rtl);
            baseResult = new BaseResult(StatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }



}
