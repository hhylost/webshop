package com.hhy.shop.biz.dal.user.impl;

import com.hhy.shop.biz.dal.base.BaseDAO;
import com.hhy.shop.biz.dal.user.UserDAO;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;

/**
 * Created by lenovo on 2016/10/17.
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    public UserDO getUserName(Long userId) {
        return (UserDO) this.getSqlMapClientTemplate().queryForObject("user.queryById", userId);
    }
}
