package ief.utils;

import java.util.Map;

import ief.domain.UploadBooksDO;
import ief.dto.params.ListBooksParam;

public class SqlProvider {
	
	public String getBooksByPara(ListBooksParam listBooksParam) {
		String result="select bookName,bookId,userId,onLoan,comment,createdTime,wantedNum,"
				+ "bookCoverImg,category,district,street,userHeadImg,userName,lon,lat from "
				+ "upload_books where bookId<"+listBooksParam.getLastId();
		if(listBooksParam.getCity()!=null){
			result+=" and city= '"+listBooksParam.getCity()+"'";
		}
		result+=" order by bookId desc limit "+listBooksParam.getPageSize();
		return result;
	}
	
	public String getBooksBySameName(Map<String, Object> para) {
		ListBooksParam listBooksParam=(ListBooksParam)para.get("listBooksParam");
		UploadBooksDO addBookParam=(UploadBooksDO)para.get("uploadBooksDO");
		String result="select bookName,bookId,userId,onLoan,comment,createdTime,wantedNum,"
				+ "bookCoverImg,category,district,street,userHeadImg,userName,lon,lat from "
				+ "upload_books where bookId<"+listBooksParam.getLastId()+" and bookName='"+addBookParam.getBookName()+"' and bookId!="+addBookParam.getBookId();

		result+=" order by bookId desc limit "+listBooksParam.getPageSize();
		System.out.println(result);
		return result;
	}
}
