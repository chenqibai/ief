package ief.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ief.domain.BooksWantedDO;
import ief.dto.params.BaseParam;
import ief.dto.params.BookWantedPara;
import ief.persistence.BooksWantedMapper;
import ief.persistence.UploadBooksMapper;
import ief.persistence.UserInfoMapper;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
@Service
public class BooksWantedService {
    @Autowired
    private BooksWantedMapper booksWantedMapper;
    @Autowired
    private UploadBooksMapper uploadBooksMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    

    public List<BooksWantedDO> getBooksWanted(){
        return booksWantedMapper.getBooksWanted();
    }
    
    @Transactional  
    public int markerWanted(BaseParam baseParam, BookWantedPara wantedPara){
    	if(wantedPara.getWantedFlag()!=null&&wantedPara.getWantedFlag()==1){
    		try{booksWantedMapper.markerWanted(baseParam.getUserId(), wantedPara.getBookId());
    			uploadBooksMapper.markWanted(1, wantedPara.getBookId());
    			userInfoMapper.markWanted(1,0 ,baseParam.getUserId());
    			return 1;
        	}catch(DuplicateKeyException e){
    			return -1;
    		}
    	}else if(wantedPara.getWantedFlag()!=null&&wantedPara.getWantedFlag()==0){
    		int del=booksWantedMapper.markerNotWanted(baseParam.getUserId(), wantedPara.getBookId());
    		uploadBooksMapper.markWanted(-1, wantedPara.getBookId());
			userInfoMapper.markWanted(-1,0, baseParam.getUserId());
    		return del==1?2:-2;
    	}else{
    		return -3;
    	}
    	
        
    }
}
