package ief.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ief.domain.UploadBooksDO;
import ief.dto.params.BaseParam;
import ief.dto.params.BookWantedPara;
import ief.dto.params.ListBooksParam;
import ief.dto.results.BaseResult;
import ief.dto.results.BookDetailResult;
import ief.dto.results.BooksOwnedResult;
import ief.dto.results.BooksWantedResult;
import ief.dto.results.CodeResult;
import ief.dto.results.ListBooksResult;
import ief.enums.CategoryEnum;
import ief.enums.StatusEnum;
import ief.service.BooksService;
import ief.service.BooksWantedService;
import ief.utils.ControllerUtil;
import ief.utils.HttpUtil;
import ief.utils.JsonUtil;

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

    @RequestMapping(value = "/app/list_books")
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

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/add_book")
    public void add_books(
            BaseParam baseParam,
            UploadBooksDO addBookParam,
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
        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/edit_book")
    public void edit_book(
            BaseParam baseParam,
            UploadBooksDO addBookParam,
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

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
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

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/get_book_detail")
    public void get_book_detail(
            BaseParam baseParam,
            String bookId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        HttpUtil.logRequest(logger, "get_book_detail", httpServletRequest);
        BaseResult baseResult = null;
        BookDetailResult uploadBookDetailResult = null;
        try {
            uploadBookDetailResult = booksService.getBookDetail(baseParam, bookId);
            baseResult = new BaseResult(StatusEnum.SUCCESS, uploadBookDetailResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }
        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/mark_wanted")
    public void mark_wanted(
            BaseParam baseParam,
            BookWantedPara wantedPara,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        HttpUtil.logRequest(logger, "mark_wanted", httpServletRequest);
        BaseResult baseResult = null;
        try {
        	if(wantedPara.getWantedFlag()==null||(wantedPara.getWantedFlag()!=0&&wantedPara.getWantedFlag()!=1))
        		baseResult = new BaseResult(StatusEnum.PARA_ERR);
        	else{
        		int rtl = booksWantedService.markerWanted(baseParam, wantedPara);
                switch (rtl){
                	case 1:
                		baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(0, "添加成功"));
                		break;
                	case -1:
                		baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(1, "添加想读已经存在"));
                		break;
                	case 2:
                		baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(0, "删除成功"));
                		break;
                	case -2:
                		baseResult = new BaseResult(StatusEnum.SUCCESS, new CodeResult(1, "删除失败"));
                		break;
                	case -3:
                		baseResult = new BaseResult(StatusEnum.PARA_ERR);
                		break;
                	default:
                		baseResult = new BaseResult(StatusEnum.PARA_ERR,new CodeResult(1, "未知错误"));
                		break;
                }
        	}
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }
        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/books_wanted")
    public void books_wanted(
            BaseParam baseParam,
            Long userId,
            ListBooksParam listBooksParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {

        HttpUtil.logRequest(logger, "books_wanted", httpServletRequest);
        BaseResult baseResult = null;
        try {
            List<BooksWantedResult> list = booksService.getBooksWantedByUserId(userId,listBooksParam);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }

    @RequestMapping(value = "/app/books_owned")
    public void books_owned(
            BaseParam baseParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        BaseResult baseResult = null;
        try {
            List<BooksOwnedResult> list = booksService.getBooksOwnedByUserId(baseParam.getUserId());
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }


    @RequestMapping(value = "/app/is_wanted")
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
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }

        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }
    
    @RequestMapping(value = "/app/get_same_books")
    public void get_same_books(
            BaseParam baseParam,UploadBooksDO addBookParam,ListBooksParam listBooksParam,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        BaseResult baseResult = null;
        try {
            List<ListBooksResult> list = booksService.getSameBooks(listBooksParam,addBookParam);
            baseResult = new BaseResult(StatusEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResult = new BaseResult(StatusEnum.FAILED);
        }
        ControllerUtil.responseWriter(httpServletResponse, JsonUtil.toString(baseResult));
    }
}
