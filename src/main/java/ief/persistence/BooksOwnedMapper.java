package ief.persistence;

import ief.dto.results.BooksOwnedResult;
import ief.dto.results.BooksWantedResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangdongsheng on 15/7/5.
 */
public interface BooksOwnedMapper {
    @Select("select count(*) from books_owned where keyUserId=#{keyUserId}")
    public Integer getBooksOwnedNum(@Param("keyUserId")Long keyUserId);

    @Insert("insert into books_owned(id, bookId, keyUserId, createdTime) values (null, #{uploadBookId}, #{userId}, sysDate())")
    public int addOwnedBook(@Param("userId")Long userId, @Param("uploadBookId")Long uploadBookId);

    @Select("select ub.bookCoverImg, ub.bookName, ub.comment, ub.category as categoryId, ub.id as bookId from books_owned bw inner join upload_books ub on bw.bookId = ub.id where bw.keyUserId = #{userId}")
    public List<BooksOwnedResult> getBooksOwnedByUserId(@Param("userId")Long userId);

}
