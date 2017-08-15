package com.hhy.shop.biz.dal.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * DAO父类
 *
 * @author hehy
 * @create 2017-02-21
 **/
public class BaseDAO extends SqlMapClientDaoSupport {
    @SuppressWarnings("unchecked")
    protected <T> PageQuery<T> listByQuery(String statementName, PageQuery<T> query) {
        // 获取总记录数
        query.setTotalRecord(countByQuery(statementName + "_count", query));
        // 如果有记录
        if (query.getTotalRecord() > 0) {
            // 避免分页Index错误，导致出现-1的BUg
            Integer pageIndex = query.getPageIndex() - 1;
            if (pageIndex < 0) {
                pageIndex = 0;
            }
            query.setRecordIndex(pageIndex * query.getPageSize());
            List<T> result = getSqlMapClientTemplate().queryForList(statementName, query);
            if (result == null) {
                result = new ArrayList<T>();
            }
            query.setDataList(result);
        }
        return query;
    }

    /**
     * 获取总数
     *
     * @param statementName
     * @param query
     * @return
     */
    public <T> Integer countByQuery(String statementName, PageQuery<T> query) {
        return (Integer) getSqlMapClientTemplate().queryForObject(statementName, query);
    }
}
