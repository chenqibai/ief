package ief.controller;

import com.alibaba.fastjson.JSON;
import ief.constants.Config;
import ief.enums.StatusEnum;
import ief.dto.params.BaseParam;
import ief.dto.results.BaseResult;
import ief.utils.ControllerUtil;
import ief.utils.HttpUtil;
import ief.utils.ImgUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
@Controller
public class ImageController {
    private final Log logger = LogFactory.getLog(ImageController.class);

    /**
     * 图片上传
     *
     * @param file 图片
     * @param type 类型 0：人头像 1：书封面
     * @return
     */
    // 处理文件上传一
    @RequestMapping(value = "app/add_image", method = RequestMethod.POST)
    public void fileUpload(
            BaseParam baseParam,
            @RequestParam("Filedata") CommonsMultipartFile file,
            @RequestParam("type") Integer type,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        /**
         // 获取文件类型
         logger.info(file.getContentType());
         // 获取文件大小
         logger.info(file.getSize());
         // 获取文件名称
         logger.info(file.getOriginalFilename());
         */
        logger.info(HttpUtil.getFullURL(httpServletRequest));

        try {
            String path = null;
            String endPath = null;
            switch (type) {
                case 0:
                    endPath = "pers/" + baseParam.getUserId() + file.getOriginalFilename();
                    break;
                case 1:
                    endPath = "book/" + baseParam.getUserId() + file.getOriginalFilename();
                    break;
                default:
                    break;
            }
            // 判断文件是否存在
            if (!file.isEmpty()) {
                logger.info(path);
                path = ImgUtil.generateFullPath(endPath);
                File localFile = new File(path);
                try {
                    file.transferTo(localFile);
                    ArrayList<String> list = new ArrayList<>(1);
                    list.add(endPath);
                    BaseResult baseResult = new BaseResult(StatusEnum.SUCCESS, list);
                    logger.info(JSON.toJSONString(baseResult));
                    ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
                } catch (IllegalStateException e) {
                    logger.error(e.getMessage(), e);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(new BaseResult(StatusEnum.FAILED, null)));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    @RequestMapping("app/download_img")
    public void download(String fileName, HttpServletRequest request,
                         String endPath,
                           HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        try {
            String path = Config.getInstance().getProperty("IMG_ADDRESS");
            InputStream inputStream = new FileInputStream(new File(path + endPath));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
    }
}
