package ief.controller;

import com.alibaba.fastjson.JSON;
import ief.dto.params.AddBookParam;
import ief.dto.params.UploadBookDetailParam;
import ief.dto.results.*;
import ief.enums.CategoryEnum;
import ief.enums.StatusEnum;
import ief.dto.params.BaseParam;
import ief.dto.params.ListBooksParam;
import ief.service.BooksService;
import ief.service.BooksWantedService;
import ief.utils.ControllerUtil;
import ief.utils.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
@Controller
public class BooksController {
    private final Log logger = LogFactory.getLog(BooksController.class);

    @Autowired
    private BooksService booksService;
    @Autowired
    private BooksWantedService booksWantedService;

    @RequestMapping(value = "/app/list_books", method = RequestMethod.POST)
    public void list_books(
            BaseParam baseParam,
            ListBooksParam listBooksParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;
        try {
            List<ListBooksResult> listBooksResult = booksService.listBooks(baseParam, listBooksParam);
            baseResult = new BaseResult(StatusEnum.SUCCESS, listBooksResult);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/add_book", method = RequestMethod.POST)
    public void add_books(
            BaseParam baseParam,
            AddBookParam addBookParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;

        try {
            int rtl = booksService.uploadBook(baseParam, addBookParam);
            baseResult = new BaseResult(StatusEnum.SUCCESS);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/edit_book", method = RequestMethod.POST)
    public void edit_book(
            BaseParam baseParam,
            AddBookParam addBookParam,
            Long bookId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;

        try {
            int rtl = booksService.editBook(baseParam, addBookParam, bookId);
            baseResult = new BaseResult(StatusEnum.SUCCESS);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/get_categories", method = RequestMethod.POST)
    public void get_categories(
            BaseParam baseParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        logger.info(HttpUtil.getFullURL(httpServletRequest));
        BaseResult baseResult = null;

        try {
            baseResult = new BaseResult(StatusEnum.SUCCESS, CategoryEnum.listAllCategoryies());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/get_book_detail", method = RequestMethod.POST)
    public void get_book_detail(
            BaseParam baseParam,
            UploadBookDetailParam uploadBookDetailParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        HttpUtil.logRequest(logger, "get_book_detail", httpServletRequest);
        BaseResult baseResult = null;
        UploadBookDetailResult uploadBookDetailResult = null;
        try {
            uploadBookDetailResult = booksService.getBookDetail(baseParam, uploadBookDetailParam);
            LinkedList<UploadBookDetailResult> list = new LinkedList<>();
            list.add(uploadBookDetailResult);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/mark_wanted", method = RequestMethod.POST)
    public void mark_wanted(
            BaseParam baseParam,
            Long uploadBookId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        HttpUtil.logRequest(logger, "mark_wanted", httpServletRequest);
        BaseResult baseResult = null;
        try {
            int rtl = booksWantedService.markerWanted(baseParam, uploadBookId);
            if (rtl == -1) {
                logger.error("添加想读不为一：" + uploadBookId + baseParam);
                baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(0, "添加成功"));
            } else {
                baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(1, "添加想读已经存在"));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/mark_not_wanted", method = RequestMethod.POST)
    public void mark_not_wanted(
            BaseParam baseParam,
            Long uploadBookId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        HttpUtil.logRequest(logger, "mark_not_wanted", httpServletRequest);
        BaseResult baseResult = null;
        try {
            int rtl = booksWantedService.markerNotWanted(baseParam, uploadBookId);
            if (rtl == 1) {
                baseResult = new BaseResult(StatusEnum.SUCCESS);
            } else {
                logger.error("删除想读不为一：" + uploadBookId + baseParam);
                baseResult = new BaseResult(StatusEnum.FAILED);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }


    @RequestMapping(value = "/app/books_wanted", method = RequestMethod.POST)
    public void books_wanted(
            BaseParam baseParam,
            Long queryUserId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        HttpUtil.logRequest(logger, "books_wanted", httpServletRequest);
        BaseResult baseResult = null;
        try {
            List<BooksWantedResult> list = booksService.getBooksWantedByUserId(queryUserId);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    @RequestMapping(value = "/app/books_owned", method = RequestMethod.POST)
    public void books_owned(
            BaseParam baseParam,
            Long queryUserId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        HttpUtil.logRequest(logger, "books_owned", httpServletRequest);
        BaseResult baseResult = null;
        try {
            List<BooksOwnedResult> list = booksService.getBooksOwnedByUserId(queryUserId);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }


    @RequestMapping(value = "/app/is_wanted", method = RequestMethod.POST)
    public void is_wanted(
            BaseParam baseParam,
            Long bookId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        HttpUtil.logRequest(logger, "is_wanted", httpServletRequest);
        BaseResult baseResult = null;
        try {
            int rtl = booksService.isWantedByBookId(baseParam.getUserId(), bookId);
            if(rtl == 1) {
                baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(1, "用户想看的类型"));
            } else if(rtl == 0){
                baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(0, "用户不想看"));
            } else{
                baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(-1, "本用户"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JSON.toJSONString(baseResult));
    }

    public static void main(String[] args) {

    }
}
