package ief.utils;

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
}
