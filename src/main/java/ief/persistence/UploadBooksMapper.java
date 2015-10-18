package ief.persistence;

import ief.domain.UploadBooksDO;
import ief.dto.params.AddBookParam;
import ief.dto.params.ListBooksParam;
import ief.dto.params.UploadBookDetailParam;
import ief.dto.results.ListBooksResult;
import ief.dto.results.UploadBookDetailResult;
import ief.dto.results.UserIdResult;
import ief.model.BookAbstractDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public interface UploadBooksMapper {
    @Select("select b.id as uploadBookId, b.bookCoverImg, u.userHeadImg, b.bookName as bookName, b.comment as comment, u.userName as userName, u.id as userID, u.locate as locate, b.wantedNum as wantedNum, b.category as categoryId from upload_books b inner join user_info u " +
            "on b.userId = u.id order by b.wantedNum, b.createdTime desc")
    public List<ListBooksResult> getBooksWanted(@Param("ListBooksParam") ListBooksParam listBooksParam );

    @Insert("insert into upload_books(id, userId, comment, bookName, bookCoverImg, category) " +
            "values (null, #{uploadBooksDO.userId}, #{uploadBooksDO.comment}, #{uploadBooksDO.bookName}, #{uploadBooksDO.bookCoverImg}, #{uploadBooksDO.category})")
    @Options(useGeneratedKeys = true, keyProperty = "uploadBooksDO.id", keyColumn ="id")
    public int addBook(@Param("uploadBooksDO") UploadBooksDO uploadBooksDO);

    @Update("update upload_books set userId = #{uploadBooksDO.userId}, comment = #{uploadBooksDO.comment}, bookName = #{uploadBooksDO.bookName}, bookCoverImg = #{uploadBooksDO.bookCoverImg}, category = #{uploadBooksDO.category}, bookCoverImg=#{uploadBooksDO.bookCoverImg} where id=#{bookId}")
    public int editBook(@Param("uploadBooksDO") UploadBooksDO uploadBooksDO, @Param("bookId") Long bookId);


    @Select("select b.userId as userId, b.bookCoverImg, u.userHeadImg, b.bookName as bookName, b.comment as comment, u.userName as userName, u.locate as locate, u.ownedNum as ownedNum, b.wantedNum as wantedNum,b.category as categoryId from upload_books b inner join user_info u " +
            "on b.id = #{uploadBookDetailParam.uploadBookId} and b.userId = u.id")
    public UploadBookDetailResult getUploadBookDetail(@Param("uploadBookDetailParam") UploadBookDetailParam uploadBookDetailParam);

    @Select("select userId from upload_books where id = #{bookId}")
    public UserIdResult getUserIdByBookId(@Param("bookId") Long bookId);


    @Select("select bb.userId as userId, bb.comment, uu.userName, uu.userHeadImg from upload_books bb inner join user_info uu on bb.bookName = #{bookName} and bb.userId = uu.id and bb.userId != #{userId}")
    public List<BookAbstractDO> listBookAbstracts(@Param("bookName")String bookName, @Param("userId")Long userId);



}
