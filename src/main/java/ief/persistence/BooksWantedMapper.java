package ief.persistence;

import ief.domain.BooksWantedDO;
import ief.dto.params.ListBooksParam;
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

    @Select("select ub.bookId, ub.bookCoverImg, ub.bookName, ub.comment, ub.category, ub.userHeadImg, ub.userName,bw.createdTime from books_wanted bw " +
            ",upload_books ub where bw.userId = #{userId} and bw.bookId = ub.bookId and bw.createdTime<#{listBooksParam.createdTime} order by bw.createdTime limit #{listBooksParam.pageSize}")
    public List<BooksWantedResult> getBooksWantedByUserId(@Param("userId")Long userId,@Param("listBooksParam") ListBooksParam listBooksParam);

    @Insert("insert into books_wanted( bookId, userId, createdTime) values ( #{uploadBookId}, #{userId}, now())")
    public int markerWanted(@Param("userId")Long userId, @Param("uploadBookId")Long uploadBookId);

    @Insert("delete from books_wanted where userId = #{userId} and bookId=#{uploadBookId}")
    public int markerNotWanted(@Param("userId")Long userId, @Param("uploadBookId")Long uploadBookId);

    @Select("select count(*) from books_wanted where bookId=#{bookId} and userId=#{keyUserId}")
    public Integer getBooksWantedNumByUserIdAndBookId(@Param("bookId") Long bookId, @Param("keyUserId") Long keyUserId);

}
