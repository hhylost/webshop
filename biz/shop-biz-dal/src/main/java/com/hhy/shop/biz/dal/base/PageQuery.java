package com.hhy.shop.biz.dal.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Type BaseQuery
 * @Desc 查询分页基类
 * @author hehy.he
 * @date 2017-06-17
 * @Version V1.0
 */
public class PageQuery<T> {

    /**
     * 执行所有查询语句后返回的结果集,与DAO中的listByQuery的返回值对应
     */
    protected List<T> dataList;

    /** 默认每页显示的记录数 */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 一页大小
     */
    protected int pageSize;
    /**
     * 总记录数
     */
    protected int totalRecord;
    /**
     * 当前页数，从 0开始，0代表第一页
     */
    protected int pageIndex;

    /**
     * 总页数
     */
    protected int totalPage;
    /**
     * 当前页下首条数据在数据库中的index
     */
    protected int recordIndex;

    /**
     * 是否分页
     */
    protected boolean isPagination = true;

    /**
     * 是否状态取反
     */
    protected boolean isStateInvert = false;

    /**
     * 多状态
     */
    protected List<Integer> states;

    public PageQuery() {
        // 构造函数
    }

    public PageQuery(int pageIndex, int pageSize) {
        setPageIndex(pageIndex);
        setPageSize(pageSize);
    }

    public PageQuery(boolean isPagination) {
        setPagination(isPagination);
    }

    public int getRecordIndex() {
        return recordIndex;
    }

    public void setRecordIndex(int recordIndex) {
        this.recordIndex = recordIndex;
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
     * 总记录数
     *
     * @return
     */
    public int getTotalRecord() {
        return totalRecord;
    }

    /**
     * 设置总的记录数。计算出总页数
     *
     * @param totalRecord
     */
    public void setTotalRecord(int totalRecord) {
        this.totalPage = (totalRecord + getPageSize() - 1) / getPageSize();
        this.totalRecord = totalRecord;
    }

    /**
     * 得到当前查询的第几页
     *
     * @return
     */
    public int getPageIndex() {
        if (pageIndex < 1) {
            pageIndex = 0;
        } else if (pageIndex > getTotalPage()) {
            pageIndex = getTotalPage() - 1;
        }

        return pageIndex;
    }

    /**
     * 是否有下页
     *
     * @return
     */
    public boolean hasNextPage() {
        return pageIndex <= getTotalPage();
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
        return totalPage;
    }

    public List<T> getDataList() {
        if (dataList == null) {
            return Collections.<T> emptyList();
        } else {
            return dataList;
        }
    }

    public void setDataList(List<T> module) {
        this.dataList = module;
    }

    public boolean isPagination() {
        return isPagination;
    }

    public void setPagination(boolean isPagination) {
        this.isPagination = isPagination;
    }

    public boolean isStateInvert() {
        return isStateInvert;
    }

    public void setStateInvert(boolean isStateInvert) {
        this.isStateInvert = isStateInvert;
    }

    public List<Integer> getStates() {
        if (null == states) {
            states = new ArrayList<Integer>();
        }
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

}
