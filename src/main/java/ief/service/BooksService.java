package ief.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ief.domain.UploadBooksDO;
import ief.dto.params.BaseParam;
import ief.dto.params.ListBooksParam;
import ief.dto.results.BookDetailResult;
import ief.dto.results.BooksOwnedResult;
import ief.dto.results.BooksWantedResult;
import ief.dto.results.ListBooksResult;
import ief.dto.results.UserInfoResult;
import ief.persistence.BooksWantedMapper;
import ief.persistence.UploadBooksMapper;
import ief.persistence.UserInfoMapper;
import ief.utils.DistanceUtil;
import ief.utils.HttpUtil;
import ief.utils.JsonUtil;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
@Service
public class BooksService {
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    UploadBooksMapper uploadBooksMapper;
    @Autowired
    BooksWantedMapper booksWantedMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    private UserService userService;

    public List<ListBooksResult> listBooks(BaseParam baseParam, ListBooksParam listBooksParam) throws Exception{
        //TODO 获得用户所在城市
//        String text = HttpUtil.get(BaiduAddressUtil.BAIDU_GET_DIS +  baseParam.getLat() + "," + baseParam.getLon());
//        listBooksParam.setCity(BaiduAddressUtil.getCity(text));
        if(listBooksParam.getLastId()==null)
        	listBooksParam.setLastId(""+Long.MAX_VALUE);
        List<ListBooksResult> result =new LinkedList<ListBooksResult>();
        List<ListBooksResult> list = uploadBooksMapper.getBooksByPara(listBooksParam);
        List<ListBooksResult> noLonLatList=new LinkedList<ListBooksResult>();
        if(baseParam.getLat()!=null&baseParam.getLon()!=null){//如上传地理位置信息则按地理位置排序
        	List<Map.Entry<Double,ListBooksResult>> mappingList = null; 
        	Map<Double,ListBooksResult> map = new HashMap<Double,ListBooksResult>(); 
        	for(ListBooksResult book: list){//计算每个距离
        		if(book.getLon()!=null&&book.getLat()!=null){
        			Double distance=DistanceUtil.GetDistance(baseParam.getLon().doubleValue(),
        				baseParam.getLat().doubleValue(), book.getLon().doubleValue(), book.getLat().doubleValue());
        			map.put(distance, book); 
        		}else{
        			noLonLatList.add(book);
        		}
        	}
        	//通过ArrayList构造函数把map.entrySet()转换成list 
			mappingList = new ArrayList<Map.Entry<Double,ListBooksResult>>(map.entrySet()); 
			//通过比较器实现比较排序 
			Collections.sort(mappingList, new Comparator<Map.Entry<Double,ListBooksResult>>(){ 
				public int compare(Map.Entry<Double,ListBooksResult> mapping1,Map.Entry<Double,ListBooksResult> mapping2){ 
					return mapping1.getKey().compareTo(mapping2.getKey()); 
				}
			});
			for(Map.Entry<Double,ListBooksResult> entry:mappingList){//加入地理位置信息排序的
				result.add(entry.getValue());
			}
        	result.addAll(noLonLatList);//加上沒有地理位置信息的
        }else{
        	result.addAll(list);//不排序全加上
        }
        return result;
    }
    
    @Transactional
    public int uploadBook(BaseParam baseParam, UploadBooksDO addBookParam) throws ClientProtocolException, IOException{
        UploadBooksDO uploadBooksDO = new UploadBooksDO();
        uploadBooksDO.setUserId(baseParam.getUserId());
        uploadBooksDO.setComment(addBookParam.getComment());
        uploadBooksDO.setBookName(addBookParam.getBookName());
        uploadBooksDO.setBookCoverImg(addBookParam.getBookCoverImg());
        uploadBooksDO.setCategory(addBookParam.getCategory());
        UserInfoResult userInfoResult = userService.getUserInfo(baseParam,baseParam.getUserId(),false);
        uploadBooksDO.setUserHeadImg(userInfoResult.getUserHeadImg());
        uploadBooksDO.setSex(userInfoResult.getSex());
        uploadBooksDO.setConstellation(userInfoResult.getConstellation());
        uploadBooksDO.setUsername(userInfoResult.getUserName());
        if(addBookParam.getDefaultPlace()==null){//没有上传是默认地点
        	uploadBooksDO.setLon(userInfoResult.getLon());
        	uploadBooksDO.setLat(userInfoResult.getLat());
        	uploadBooksDO.setDistrict(userInfoResult.getDistrict());
        	uploadBooksDO.setStreet(userInfoResult.getStreet());
        	uploadBooksDO.setCity(userInfoResult.getCity());
        	uploadBooksDO.setDefaultPlace(userInfoResult.getDefaultPlace());
        }else{//新地点
        	uploadBooksDO.setLat(baseParam.getLat());
            uploadBooksDO.setLon(baseParam.getLon());
            uploadBooksDO.setDefaultPlace(addBookParam.getDefaultPlace());
            String text = HttpUtil.get(HttpUtil.BAIDU_GET_DIS +  baseParam.getLat() + "," + baseParam.getLon());
    		Map<String,Object> locationMap=JsonUtil.toBean(text, Map.class);
    		Map<String,Object> addressComponent=(Map<String,Object>)((Map<String,Object>)locationMap.get("result")).get("addressComponent");
            //城市，区，街道
    		uploadBooksDO.setDistrict((String)addressComponent.get("district"));
    		uploadBooksDO.setStreet((String)addressComponent.get("street"));
    		uploadBooksDO.setCity((String)addressComponent.get("city"));
        }
        int rtl = uploadBooksMapper.addBook(uploadBooksDO);
        int rt2=userInfoMapper.markWanted(0,1,baseParam.getUserId());
        return rtl;
    }

    public int editBook(BaseParam baseParam, UploadBooksDO addBookParam, Long bookId){
        UploadBooksDO uploadBooksDO = new UploadBooksDO();
        uploadBooksDO.setComment(addBookParam.getComment());
//        uploadBooksDO.setBookName(addBookParam.getBookName());
        uploadBooksDO.setBookCoverImg(addBookParam.getBookCoverImg());
        uploadBooksDO.setCategory(addBookParam.getCategory());
        int rtl = uploadBooksMapper.editBook(uploadBooksDO, bookId);
        return rtl;
    }

    public BookDetailResult getBookDetail(BaseParam baseParam, String bookId){
    	BookDetailResult uploadBookDetailResult = uploadBooksMapper.getUploadBookDetail(bookId);
        return uploadBookDetailResult;
    }


    public List<BooksWantedResult> getBooksWantedByUserId(Long userId,ListBooksParam listBooksParam){
    	listBooksParam.setPageIndex((listBooksParam.getPageIndex()-1)*listBooksParam.getPageSize());
        System.out.println(listBooksParam.getPageIndex()+"\t"+listBooksParam.getPageSize()+"\t"+userId);
    	List<BooksWantedResult> booksWantedResult = booksWantedMapper.getBooksWantedByUserId(userId,listBooksParam);
        return booksWantedResult;
    }

    public List<BooksOwnedResult> getBooksOwnedByUserId(Long userId){
        List<BooksOwnedResult> booksOwnedResultList = uploadBooksMapper.getBooksByUserId(userId);
        return booksOwnedResultList;
    }

    public int isWantedByBookId(Long userId, Long bookId){
        int rtl = booksWantedMapper.getBooksWantedNumByUserIdAndBookId(bookId, userId);
        if(rtl==0)
        	return 0;
        else
        	return 1;
    }
    public List<ListBooksResult> getSameBooks(ListBooksParam listBooksParam,UploadBooksDO addBookParam){
    	listBooksParam.setPageIndex((listBooksParam.getPageIndex()-1)*listBooksParam.getPageSize());
    	List<ListBooksResult> result = uploadBooksMapper.getSameBooks(listBooksParam,addBookParam);
        return result;
    }
}
