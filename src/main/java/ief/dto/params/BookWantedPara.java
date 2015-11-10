package ief.dto.params;

public class BookWantedPara {
	private Long bookId;
	private Short wantedFlag;
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Short getWantedFlag() {
		return wantedFlag;
	}
	public void setWantedFlag(Short wantedFlag) {
		this.wantedFlag = wantedFlag;
	}
	
}
