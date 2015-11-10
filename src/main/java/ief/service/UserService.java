package ief.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ief.domain.UserInfoDO;
import ief.dto.params.BaseParam;
import ief.dto.results.LoginResult;
import ief.dto.results.LogoutResult;
import ief.dto.results.RegisterUserResult;
import ief.dto.results.UserInfoResult;
import ief.persistence.BooksWantedMapper;
import ief.persistence.UploadBooksMapper;
import ief.persistence.UserInfoMapper;
import ief.utils.Constellation;
import ief.utils.DateUtil;
import ief.utils.HttpUtil;
import ief.utils.JsonUtil;
import ief.utils.Lunar;
import ief.utils.SecretUtil;
import ief.utils.SessionUtil;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
@Service
public class UserService {
	private final Log logger = LogFactory.getLog(UserService.class);
	// @Autowired
	// private IUserInfoDao userInfoDaoImpl;
	//
	// public List<UserInfoEntity> getUserInfos(){
	// List<UserInfoEntity> list = userInfoDaoImpl.getUserInfoList();
	// logger.info("zds" + list.toString());
	// return null;
	// }

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private BooksWantedMapper booksWantedMapper;
	@Autowired
	private UploadBooksMapper uploadBooksMapper;

	public List<UserInfoDO> getUserInfos() {
		return this.userInfoMapper.getUserInfo();
	}

	public int register(BaseParam baseParam, UserInfoDO registerUserParam, LoginResult loginResult) {
		registerUserParam.setPhone(SecretUtil.encrypt(registerUserParam.getPhone(),SecretUtil.PASSWORD));
		registerUserParam.setPassword(SecretUtil.encrypt(registerUserParam.getPassword(),SecretUtil.PASSWORD));
		try{
			int rtl = userInfoMapper.addUserInfo(registerUserParam);
			if (rtl == 1) {
//				loginResult.setSessionId(SessionUtil.newSessionId(registerUserParam.getUserId()));
				loginResult.setUserId(registerUserParam.getUserId());
				loginResult.setWanted(0);
			}
			return rtl;
		}catch(DuplicateKeyException e){
			return -1;
		}
		
	}
	@Transactional 
	public int registerDetail(BaseParam baseParam, UserInfoDO registerDetailUserParam,
			RegisterUserResult registerUserResult) throws Exception {
		registerDetailUserParam.setUserId(baseParam.getUserId());
		if (registerDetailUserParam.getBirthdayType() == 1) {// 阴历
			registerDetailUserParam.setLunarBirthday(registerDetailUserParam.getBirthday());
			registerDetailUserParam.setBirthday(Lunar.lunarToSolar(registerDetailUserParam.getFormatBirthday(), false));
		} else {// 阳历
			String lunarString = Lunar.solarToLunar(registerDetailUserParam.getFormatBirthday());
			registerDetailUserParam.setLunarBirthday(DateUtil.getyyyyMMddDate(lunarString));
		}
		// 星座
		int month = registerDetailUserParam.getBirthday().getMonth() + 1;
		int day = registerDetailUserParam.getBirthday().getDate();
		registerDetailUserParam.setConstellation(Constellation.getConstellation(month, day));
		//默认咖啡厅
		if(registerDetailUserParam.getDefaultPlace()!=null){
			String text = HttpUtil.get(HttpUtil.BAIDU_GET_DIS +  baseParam.getLat() + "," + baseParam.getLon());
			Map<String,Object> locationMap=JsonUtil.toBean(text, Map.class);
			System.out.println(locationMap);
    		Map<String,Object> addressComponent=(Map<String,Object>)((Map<String,Object>)locationMap.get("result")).get("addressComponent");
			System.out.println(addressComponent);
    		//城市，区，街道
			registerDetailUserParam.setDistrict((String)addressComponent.get("district"));
			registerDetailUserParam.setStreet((String)addressComponent.get("street"));
			registerDetailUserParam.setCity((String)addressComponent.get("city"));
		}
		int result = userInfoMapper.addUserInfoDetail(registerDetailUserParam);
		uploadBooksMapper.udateUserInfo(registerDetailUserParam, registerDetailUserParam.getUserId());
		registerUserResult.setSessionId(SessionUtil.getSessionId(baseParam.getUserId()));
		return result;
	}

	public int login(BaseParam baseParam, UserInfoDO loginParam, LoginResult loginResult) throws ParseException {
		loginParam.setPhone(SecretUtil.encrypt(loginParam.getPhone(),SecretUtil.PASSWORD));
		System.out.println("+++++++++++++"+loginParam.getPhone());
		UserInfoDO vo = userInfoMapper.getUserInfoByPhoneAndPassword(loginParam.getPhone());
		if (vo!=null) {
			if (!SecretUtil.decrypt(vo.getPassword(),SecretUtil.PASSWORD).equals(loginParam.getPassword()))
				return 2;//密码不正确
			loginResult.setUserId(vo.getUserId());
			loginResult.setWanted(vo.getWantedNum());
			loginResult.setOwned(vo.getOwnedNum());
			loginResult.setUserName(vo.getUserName());
			loginResult.setSex(vo.getSex());
			loginResult.setBirthday(vo.getBirthday());
			loginResult.setBirthdayType(vo.getBirthdayType());
			loginResult.setHometown(vo.getHometown());
			loginResult.setLocate(vo.getLocate());
			loginResult.setUserHeadImg(vo.getUserHeadImg());
			loginResult.setSessionId(SecretUtil.encrypt(baseParam.getDeviceId()+"|"+vo.getUserId(), SecretUtil.AUTHPASSWORD));
			return 1;//登录成功
		} else{
			return 0;//账号不存在
		}
	}

	public LogoutResult logout(BaseParam baseParam) {
		LogoutResult logoutResult = new LogoutResult();
		logoutResult.setSessionId(SessionUtil.removeSessionId(baseParam.getUserId()));
		return logoutResult;
	}

	public UserInfoResult getUserInfo(BaseParam baseParam,Long userId,boolean flag) throws ClientProtocolException, IOException {
		UserInfoResult userInfoResult = userInfoMapper.getUserInfoByUserId(userId);
		//用户当前所在城市
		if(flag&&baseParam.getLat()!=null&&baseParam.getLon()!=null){
			String text = HttpUtil.get(HttpUtil.BAIDU_GET_DIS +  baseParam.getLat() + "," + baseParam.getLon());
			Map<String,Object> locationMap=JsonUtil.toBean(text, Map.class);
    		Map<String,Object> addressComponent=(Map<String,Object>)((Map<String,Object>)locationMap.get("result")).get("addressComponent");
			//城市，区，街道
			userInfoResult.setCurrentCity((String)addressComponent.get("city"));
		}
		return userInfoResult;
	}
	@Transactional 
	public int updateUserInfo(UserInfoDO updateUserInfoParam,BaseParam baseParam) throws Exception {
		if(updateUserInfoParam.getLocationFlag()!=null&&updateUserInfoParam.getLocationFlag()==1){//修改默认咖啡厅
			//默认咖啡厅
			String text = HttpUtil.get(HttpUtil.BAIDU_GET_DIS +  baseParam.getLat() + "," + baseParam.getLon());
			Map<String,Object> locationMap=JsonUtil.toBean(text, Map.class);
			Map<String,Object> addressComponent=(Map<String,Object>)((Map<String,Object>)locationMap.get("result")).get("addressComponent");
			//城市，区，街道
			updateUserInfoParam.setDistrict((String)addressComponent.get("district"));
			updateUserInfoParam.setStreet((String)addressComponent.get("street"));
			updateUserInfoParam.setCity((String)addressComponent.get("city"));
		}
		if(updateUserInfoParam.getBirthdayFlag()!=null&&updateUserInfoParam.getBirthdayFlag()==1){
			if (updateUserInfoParam.getBirthdayType() == 1) {// 阴历
				updateUserInfoParam.setLunarBirthday(updateUserInfoParam.getBirthday());
				updateUserInfoParam.setBirthday(Lunar.lunarToSolar(updateUserInfoParam.getFormatBirthday(), false));
			} else {// 阳历
				String lunarString = Lunar.solarToLunar(updateUserInfoParam.getFormatBirthday());
				updateUserInfoParam.setLunarBirthday(DateUtil.getyyyyMMddDate(lunarString));
			}
			// 星座
			int month = updateUserInfoParam.getBirthday().getMonth() + 1;
			int day = updateUserInfoParam.getBirthday().getDate();
			updateUserInfoParam.setConstellation(Constellation.getConstellation(month, day));
		}
		uploadBooksMapper.udateUserInfo(updateUserInfoParam, updateUserInfoParam.getUserId());
		return userInfoMapper.updateUserInfo(updateUserInfoParam);
	}

}
