package ief.persistence;

import ief.domain.UserInfoDO;
import ief.dto.params.RegisterDetailUserParam;
import ief.dto.params.RegisterUserParam;
import ief.dto.params.UpdateUserInfoParam;
import ief.dto.results.UserInfoResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
public interface UserInfoMapper {
    @Select("SELECT * FROM user_info")
    List<UserInfoDO> getUserInfo();

    @Select("SELECT count(*) FROM user_info where userName=#{username}")
    Integer countUserInfoByName(@Param("userName")String username);

    @Select("SELECT count(*) FROM user_info where phone=#{phone}")
    Integer countUserInfoByPhone(@Param("phone")String phone);

    @Select("SELECT count(*) FROM user_info where id=#{id}")
    Integer countUserInfoById(@Param("id")Long id);

    @Insert("update user_info set userName = #{registerDetailUserParam.userName}, birthday = #{registerDetailUserParam.birthday}, " +
            "birthdayType = #{registerDetailUserParam.birthdayType}, userHeadImg = #{registerDetailUserParam.userHeadImg} where" +
            " id = #{registerDetailUserParam.userIdParam}")
    public int addUserInfoDetail(@Param("registerDetailUserParam")RegisterDetailUserParam registerDetailUserParam);

    @Insert("update user_info set userName = #{userInfoDO.userName}, sex = #{userInfoDO.sex}, birthday = #{userInfoDO.birthday}, " +
            "birthdayType = #{userInfoDO.birthdayType}, hometown = #{userInfoDO.hometown}, locate = #{userInfoDO.locate}, school = #{userInfoDO.school}, " +
            " userHeadImg = #{userInfoDO.userHeadImg} where id = #{userInfoDO.id}")
    public int updateUserInfo(@Param("userInfoDO")UpdateUserInfoParam userInfoDO);

    @Insert("update user_info set locate = #{locate} where id = #{id}")
    public int updateLocate(@Param("locate")String locate, @Param("id")Long id);

    @Insert("insert into user_info(id, phone, password) " +
            "values(null, #{registerUserParam.phone}, #{registerUserParam.password})")
    @Options(useGeneratedKeys = true, keyProperty = "registerUserParam.id", keyColumn ="id")
    public int addUserInfo(@Param("registerUserParam")RegisterUserParam registerUserParam);


    @Select("SELECT * FROM user_info where phone=#{phone}")
    List<UserInfoDO> getUserInfoByPhoneAndPassword(@Param("phone")String phone);

    @Select("SELECT userHeadImg, userName, locate, sex, birthday, birthdayType, hometown, school FROM user_info where id=#{userId}")
    public UserInfoResult getUserInfoByUserId(@Param("userId")Long userId);

    @Select("SELECT count(*) from user_info where phone=#{phone}")
    public int getNumByPhone(@Param("phone")String phone);


}
