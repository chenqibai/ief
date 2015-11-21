package ief.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import ief.domain.UploadBooksDO;
import ief.domain.UserInfoDO;
import ief.dto.params.ListBooksParam;
import ief.dto.results.BookDetailResult;
import ief.dto.results.BooksOwnedResult;
import ief.dto.results.ListBooksResult;
import ief.utils.SqlProvider;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public interface UploadBooksMapper {

//    @Select("select bookName,bookId,userId,onLoan,comment,createdTime,wantedNum,bookCoverImg,category,district,street,userHeadImg,userName,lon,lat from upload_books" +
//            " where city=#{listBooksParam.city} and bookId<#{listBooksParam.lastId} order by bookId desc limit #{listBooksParam.pageSize}")
	@SelectProvider(type = SqlProvider.class, method = "getBooksByPara")
    public List<ListBooksResult> getBooksByPara( ListBooksParam listBooksParam );
    
    @Select("select bookName,bookId,userId,onLoan,comment,createdTime,wantedNum,bookCoverImg,category,"
    		+ "district,street,userHeadImg,userName,defaultPlace from upload_books where userId=#{userId}")
    public List<BooksOwnedResult> getBooksByUserId(@Param("userId") long userId );
    
    @Insert("insert into upload_books(userId, comment, bookName, bookCoverImg, category,lon,lat,district,street,city,defaultPlace,createdTime,sex,constellation,userHeadImg,userName) " +
            "values (#{uploadBooksDO.userId}, #{uploadBooksDO.comment}, #{uploadBooksDO.bookName}, #{uploadBooksDO.bookCoverImg}, #{uploadBooksDO.category}"
            + ", #{uploadBooksDO.lon}, #{uploadBooksDO.lat}, #{uploadBooksDO.district}, #{uploadBooksDO.street}, #{uploadBooksDO.city}, #{uploadBooksDO.defaultPlace}"
            + ", #{uploadBooksDO.createdTime}, #{uploadBooksDO.sex}, #{uploadBooksDO.constellation}, #{uploadBooksDO.userHeadImg}, #{uploadBooksDO.userName})")
    @Options(useGeneratedKeys = true, keyProperty = "uploadBooksDO.bookId", keyColumn ="bookId")
    public int addBook(@Param("uploadBooksDO") UploadBooksDO uploadBooksDO);

    @Update("update upload_books set comment = #{uploadBooksDO.comment}, bookCoverImg = #{uploadBooksDO.bookCoverImg},"
    		+ " category = #{uploadBooksDO.category}, bookCoverImg = #{uploadBooksDO.bookCoverImg}"
    		+ ", city = #{uploadBooksDO.city}, district = #{uploadBooksDO.district}, street = #{uploadBooksDO.street}"
    		+ ", defaultPlace = #{uploadBooksDO.defaultPlace}, lon = #{uploadBooksDO.lon}, lat = #{uploadBooksDO.lat}"
    		+ ", bookCoverImg = #{uploadBooksDO.bookCoverImg} where bookId=#{bookId}")
    public int editBook(@Param("uploadBooksDO") UploadBooksDO uploadBooksDO, @Param("bookId") Long bookId);

    @Update("update upload_books set wantedNum = wantedNum+#{num} where bookId=#{bookId}")
    public int markWanted(@Param("num") int num, @Param("bookId") Long bookId);
    
    @Update("update upload_books set userName = #{userInfoDO.userName}, userHeadImg = #{userInfoDO.userHeadImg}, sex = #{userInfoDO.sex},constellation=#{userInfoDO.constellation} where userId=#{userId}")
    public int udateUserInfo(@Param("userInfoDO") UserInfoDO userInfoDO, @Param("userId") Long userId);

    @Select(" select ub.bookName,ub.bookId,ub.userId,ub.onLoan,ub.comment,ub.createdTime,ub.wantedNum,ub.bookCoverImg,"
    		+ "ub.category,ub.district,ub.street,ub.constellation,ub.sex,ub.city,ub.defaultPlace,ub.userHeadImg,"
    		+ "ui.userName userName,ui.ownedNum from upload_books ub,user_info ui where bookId=#{bookId} and ub.userId=ui.userId")
    public BookDetailResult getUploadBookDetail(@Param("bookId") String bookId);

    @SelectProvider(type = SqlProvider.class, method = "getBooksBySameName")
    public List<ListBooksResult> getSameBooks(@Param("listBooksParam") ListBooksParam listBooksParam,@Param("uploadBooksDO") UploadBooksDO addBookParam);

}
