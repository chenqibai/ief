package ief.dto.params;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class ListBooksParam {
    private Integer pageIndex;
    private Integer pageSize=15;
    private String city;
    private String lastId;
    
    public String getLastId() {
		return lastId;
	}
	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    public Integer getPageIndex() {
        return pageIndex;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "ListBooksParam{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
