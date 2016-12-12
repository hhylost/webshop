package com.hhy.shop.user.service.share.base;

/**
 * Created by lenovo on 2016/12/12.
 */
public class QueryResultDTO<T> extends ResponseDTO<T> {
    /** 默认每页显示的记录数 */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /** 每页最多显示的记录数 */
    public static final int MAX_PAGE_SIZE = 200;

    /**
     * 一页大小
     */
    private int pageSize;

    /**
     * 当前页数，从 0开始，0代表第一页
     */
    private int pageIndex;

    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总记录数
     */
    protected int totalRecord;

    /**
     * 总记录数
     */
    public void setTotal(int total) {
        this.totalPage = (total + getPageSize() - 1) / getPageSize();
        this.totalRecord = total;
    }

    /**
     * 获取一页的记录数
     *
     * @return
     */
    public int getPageSize() {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    /**
     * 设置一页的记录数
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 得到当前查询的第几页
     *
     * @return
     */
    public int getPageIndex() {
        if (pageIndex < 1) {
            pageIndex = 0;
        }
        return pageIndex;
    }

    /**
     * 是否有下页
     *
     * @return
     */
    public boolean hasNextPage() {
        if (pageIndex <= getTotalPage()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置当前页面
     *
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getStartPos() {
        return getPageIndex() * getPageSize();
    }

    public int getEndPos() {
        if (getPageIndex() * getPageSize() < getTotalRecord()) {
            return getPageIndex() * getPageSize();
        } else {
            return getTotalRecord();
        }
    }

    /**
     * 得到总页数
     *
     * @return
     */
    public int getTotalPage() {
        if (totalRecord > 0) {
            this.totalPage = (totalRecord + getPageSize() - 1) / getPageSize();
        }
        return totalPage;
    }

    /**
     * @return the totalRecord
     */
    public int getTotalRecord() {
        return totalRecord;
    }

    /**
     * @param totalRecord the totalRecord to set
     */
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
