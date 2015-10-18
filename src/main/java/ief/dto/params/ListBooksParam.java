package ief.dto.params;

/**
 * Created by zhangdongsheng on 15/6/22.
 */
public class ListBooksParam {
    private Integer pageIndex;
    private Integer pageSize;

    public ListBooksParam() {
        pageIndex = 0;
        pageSize = 10;
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
