package ief.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ief.domain.UserInfoDO;
import ief.dto.results.UserInfoResult;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
public interface UserInfoMapper {
    @Select("SELECT * FROM user_info")
    List<UserInfoDO> getUserInfo();
    
//    @Insert("update user_info set phone = #{userInfoDO.phone}, password = #{userInfoDO.password} " +
//            "where id = #{userInfoDO.id}")
//    public int updatePassword(@Param("userInfoDO")UserInfoDO userInfoDO);

    @Insert("update user_info set userName = #{registerDetailUserParam.userName}, birthday = #{registerDetailUserParam.birthday}, lunarBirthday = #{registerDetailUserParam.lunarBirthday}, " +
            "birthdayType = #{registerDetailUserParam.birthdayType}, userHeadImg = #{registerDetailUserParam.userHeadImg} , constellation = #{registerDetailUserParam.constellation}, " +
            "sex = #{registerDetailUserParam.sex},lon= #{registerDetailUserParam.lon},lat = #{registerDetailUserParam.lat},defaultPlace = #{registerDetailUserParam.defaultPlace}," +
            "city = #{registerDetailUserParam.city},district= #{registerDetailUserParam.district},street = #{registerDetailUserParam.street}" +
            "where userId = #{registerDetailUserParam.userId}")
    public int addUserInfoDetail(@Param("registerDetailUserParam")UserInfoDO registerDetailUserParam);

    @Update("update user_info set username = #{userInfoDO.userName}, sex = #{userInfoDO.sex}, birthday = #{userInfoDO.birthday}, " +
            "birthdayType = #{userInfoDO.birthdayType}, hometown = #{userInfoDO.hometown}, locate = #{userInfoDO.locate}, career = #{userInfoDO.career}, " +
            " signature = #{userInfoDO.signature}, school = #{userInfoDO.school}, userHeadImg = #{userInfoDO.userHeadImg}"
            + " ,lon= #{userInfoDO.lon},lat = #{userInfoDO.lat}, lunarBirthday = #{userInfoDO.lunarBirthday},"
            + " constellation = #{userInfoDO.constellation},city = #{userInfoDO.city},district= #{userInfoDO.district},"
            + "street = #{userInfoDO.street},defaultPlace = #{userInfoDO.defaultPlace} where userId = #{userInfoDO.userId}")
    public int updateUserInfo(@Param("userInfoDO")UserInfoDO userInfoDO);
    
    @Update("update user_info set wantedNum =wantedNum+ #{wantedNum},ownedNum =ownedNum+ #{ownedNum} where userId = #{userId}")
    public int markWanted(@Param("wantedNum") int wantedNum,@Param("ownedNum") int ownedNum,@Param("userId")long userId);

    @Insert("insert into user_info(userId, phone, password,registTime) " +
            "values(null, #{registerUserParam.phone}, #{registerUserParam.password}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "registerUserParam.userId", keyColumn ="userId")
    public int addUserInfo(@Param("registerUserParam")UserInfoDO registerUserParam);

    @Select("SELECT * FROM user_info where phone=#{phone} limit 1")
    UserInfoDO getUserInfoByPhoneAndPassword(@Param("phone")String phone);

    @Select("SELECT userName,sex,birthday,birthdayType,hometown,locate,career,school,signature,lon,lat,"
    		+ "userHeadImg,lunarBirthday,constellation,city,district,street,defaultPlace,wantedNum,ownedNum"
    		+ " FROM user_info where userId=#{userId}")
    public UserInfoResult getUserInfoByUserId(@Param("userId")Long userId);

    @Select("SELECT count(*) from user_info where phone=#{phone}")
    public int getNumByPhone(@Param("phone")String phone);


}
