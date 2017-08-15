package com.hhy.shop.biz.dal.user.impl;

import com.hhy.shop.biz.dal.base.BaseDAO;
import com.hhy.shop.biz.dal.base.PageQuery;
import com.hhy.shop.biz.dal.user.UserDAO;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.dal.user.query.UserPageQuery;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Created by lenovo on 2016/10/17.
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    public UserDO getUserName(Long userId) {
        return (UserDO) this.getSqlMapClientTemplate().queryForObject("user.queryById", userId);
    }

    @Override
    public Long insert(UserDO userDO) {
        return (Long) this.getSqlMapClientTemplate().insert("user.insertUser", userDO);
    }

    @Override
    public PageQuery<UserDO> findByQueryCondition(UserPageQuery userPageQuery) {
        return this.listByQuery("user.listByPageQuery", userPageQuery);
    }
}
