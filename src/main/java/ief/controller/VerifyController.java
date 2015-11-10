package ief.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ief.dto.params.BaseParam;
import ief.dto.results.BaseResult;
import ief.enums.StatusEnum;
import ief.service.VerifyService;
import ief.utils.ControllerUtil;
import ief.utils.HttpUtil;
import ief.utils.JsonUtil;

/**
 * Created by zhangdongsheng on 15/7/21.
 */
@Controller
public class VerifyController {
    private final Log logger = LogFactory.getLog(VerifyController.class);

    @Autowired
    private VerifyService verifyService;

    @RequestMapping(value = "/app/verify_phone", method = RequestMethod.POST)
    public void update_user_info(
            BaseParam baseParam,
            String phone,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        HttpUtil.logRequest(logger, "verify_phone", httpServletRequest);
        BaseResult baseResult = null;
        try {
            if(verifyService.verifyPhone(phone)){
                baseResult = new BaseResult(StatusEnum.SUCCESS);
                logger.info("verify_phone:"+ phone + "->success");
            } else{
                baseResult = new BaseResult(StatusEnum.FAILED);
                logger.info("verify_phone:"+ phone + "->fail");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }
}
