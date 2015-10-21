package ief.service;

import ief.domain.UploadBooksDO;
import ief.dto.params.AddBookParam;
import ief.dto.params.BaseParam;
import ief.dto.params.ListBooksParam;
import ief.dto.params.UploadBookDetailParam;
import ief.dto.results.*;
import ief.enums.CategoryEnum;
import ief.model.BookAbstractDO;
import ief.persistence.BooksOwnedMapper;
import ief.persistence.BooksWantedMapper;
import ief.persistence.UploadBooksMapper;
import ief.persistence.UserInfoMapper;
import ief.utils.BaiduAddressUtil;
import ief.utils.HttpClientUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
@Service
public class BooksService {
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    UploadBooksMapper uploadBooksMapper;
    @Autowired
    BooksOwnedMapper booksOwnedMapper;
    @Autowired
    BooksWantedMapper booksWantedMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    public List<ListBooksResult> listBooks(BaseParam baseParam, ListBooksParam listBooksParam){
        List<ListBooksResult> list = uploadBooksMapper.getBooksWanted(listBooksParam);
        for(int i = 0; i < list.size(); i++){
            ListBooksResult listBooksResult = list.get(i);
//            if(StringUtils.isEmpty(list.get(i).getLocate())){
//                String locate = BaiduAddressUtil.getCityAndDistrict(baseParam.getLon(), baseParam.getLat());
//                userInfoMapper.updateLocate(locate, baseParam.getUserId());
//                listBooksResult.setLocate(locate);
//            }
        }
        return list;
    }

    public int uploadBook(BaseParam baseParam, AddBookParam addBookParam){
        UploadBooksDO uploadBooksDO = new UploadBooksDO();

        uploadBooksDO.setUserId(baseParam.getUserId());
        uploadBooksDO.setComment(addBookParam.getComment());
        uploadBooksDO.setBookName(addBookParam.getBookName());
        uploadBooksDO.setBookCoverImg(addBookParam.getBookCoverImg());
        uploadBooksDO.setCategory(addBookParam.getCategory());
        uploadBooksDO.setLat(new BigDecimal(baseParam.getLat()));
        uploadBooksDO.setLon(new BigDecimal(baseParam.getLon()));
        String text=null;
        try {
			text = HttpClientUtil.executeGet(BaiduAddressUtil.BAIDU_GET_DIS +  baseParam.getLat() + "," + baseParam.getLon());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
        if(text!=null){
        	uploadBooksDO.setDistrict(BaiduAddressUtil.getDistrict(text));
        	uploadBooksDO.setDistrict(BaiduAddressUtil.getStreet(text));
        }
        int rtl = uploadBooksMapper.addBook(uploadBooksDO);
        booksOwnedMapper.addOwnedBook(baseParam.getUserId(), uploadBooksDO.getId());
        return rtl;
    }

    public int editBook(BaseParam baseParam, AddBookParam addBookParam, Long bookId){
        UploadBooksDO uploadBooksDO = new UploadBooksDO();

        uploadBooksDO.setUserId(baseParam.getUserId());
        uploadBooksDO.setComment(addBookParam.getComment());
        uploadBooksDO.setBookName(addBookParam.getBookName());
        uploadBooksDO.setBookCoverImg(addBookParam.getBookCoverImg());
        uploadBooksDO.setCategory(addBookParam.getCategory());

        String locate = BaiduAddressUtil.getCityAndDistrict(baseParam.getLon(), baseParam.getLat());
        userInfoMapper.updateLocate(locate, baseParam.getUserId());
        int rtl = uploadBooksMapper.editBook(uploadBooksDO, bookId);
        return rtl;
    }

    public UploadBookDetailResult getBookDetail(BaseParam baseParam, UploadBookDetailParam uploadBookDetailParam){
        UploadBookDetailResult uploadBookDetailResult = uploadBooksMapper.getUploadBookDetail(uploadBookDetailParam);
        uploadBookDetailResult.setWantedNum(booksWantedMapper.getBooksWantedNum(uploadBookDetailResult.getUserId()));
        uploadBookDetailResult.setOwnedNum(booksOwnedMapper.getBooksOwnedNum(uploadBookDetailResult.getUserId()));
        List<BookAbstractDO> bookAbstractDOList = uploadBooksMapper.listBookAbstracts(uploadBookDetailResult.getBookName(), uploadBookDetailResult.getUserId());
        uploadBookDetailResult.setBookAbstractDOList(bookAbstractDOList);
        return uploadBookDetailResult;
    }


    public List<BooksWantedResult> getBooksWantedByUserId(Long userId){
        List<BooksWantedResult> booksWantedResult = booksWantedMapper.getBooksWantedByUserId(userId);
        return booksWantedResult;
    }

    public List<BooksOwnedResult> getBooksOwnedByUserId(Long userId){
        List<BooksOwnedResult> booksOwnedResultList = booksOwnedMapper.getBooksOwnedByUserId(userId);
        for(BooksOwnedResult booksOwnedResult : booksOwnedResultList){
            booksOwnedResult.setWantedNum(booksWantedMapper.getBooksWantedNumByBookId(booksOwnedResult.getBookId()));
        }
        return booksOwnedResultList;
    }

    public int isWantedByBookId(Long userId, Long bookId){
        UserIdResult userIdResult = uploadBooksMapper.getUserIdByBookId(bookId);
        if(userId != null && userId.equals(userIdResult.getUserId())) return -1;
        int rtl = booksWantedMapper.getBooksWantedNumByUserIdAndBookId(bookId, userId);
        if(rtl == 1) return 1;
        else return 0;
    }

}
