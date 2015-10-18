package ief.service;

import ief.domain.BooksWantedDO;
import ief.dto.params.BaseParam;
import ief.persistence.BooksWantedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangdongsheng on 15/6/21.
 */
@Service
public class BooksWantedService {
    @Autowired
    private BooksWantedMapper booksWantedMapper;

    public List<BooksWantedDO> getBooksWanted(){
        return booksWantedMapper.getBooksWanted();
    }

    public int markerWanted(BaseParam baseParam, Long uploadBookId){
        int existed = booksWantedMapper.getBooksWantedNumByUserIdAndBookId(uploadBookId, baseParam.getUserId());
        if(existed >= 1) return -1;
        else{
            booksWantedMapper.markerWanted(baseParam.getUserId(), uploadBookId);
            return 1;
        }
    }

    public int markerNotWanted(BaseParam baseParam, Long uploadBookId){
        return booksWantedMapper.markerNotWanted(baseParam.getUserId(), uploadBookId);
    }
}
