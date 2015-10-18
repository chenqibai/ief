package ief.persistence;

import ief.domain.BooksWantedDO;
import ief.dto.results.BooksWantedResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
public interface BooksWantedMapper {
    @Select("select * from books_wanted")
    public List<BooksWantedDO> getBooksWanted();

    @Select("select ub.id as bookId, ub.bookCoverImg, ub.bookName, ub.comment, ui.userHeadImg, ui.userName  from books_wanted bw " +
            "inner join upload_books ub on bw.keyUserId = #{userId} and bw.bookId = ub.id  inner join user_info ui on ub.userId = ui.id")
    public List<BooksWantedResult> getBooksWantedByUserId(@Param("userId")Long userId);

    @Select("select count(*) from books_wanted where keyUserId=#{keyUserId}")
    public Integer getBooksWantedNum(@Param("keyUserId") Long keyUserId);

    @Select("select count(*) from books_wanted where bookId=#{bookId}")
    public Integer getBooksWantedNumByBookId(@Param("bookId") Long bookId);

    @Select("select count(*) from books_wanted where bookId=#{bookId} and keyUserId=#{keyUserId}")
    public Integer getBooksWantedNumByUserIdAndBookId(@Param("bookId") Long bookId, @Param("keyUserId") Long keyUserId);

    @Insert("insert into books_wanted(id, bookId, keyUserId, createdTime) values (null, #{uploadBookId}, #{userId}, sysDate())")
    public int markerWanted(@Param("userId")Long userId, @Param("uploadBookId")Long uploadBookId);

    @Insert("delete from books_wanted where keyUserId = #{userId} and bookId=#{uploadBookId}")
    public int markerNotWanted(@Param("userId")Long userId, @Param("uploadBookId")Long uploadBookId);



}
