package edu.lingnan.talklater.request;

/**
 * Description:
 * date: 2021/3/12 10:32
 *
 * @author likunzhu
 * @since
 */
public class QueryEntity {

    /**
     * 当前查询页下表
     */
    private int pageNum;

    /**
     * 每页包含多少条数据
     */
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
