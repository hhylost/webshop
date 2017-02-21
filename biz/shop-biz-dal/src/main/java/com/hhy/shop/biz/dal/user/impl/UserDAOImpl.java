package com.hhy.shop.biz.dal.user.impl;

import com.hhy.shop.biz.dal.user.UserDAO;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Created by lenovo on 2016/10/17.
 */
public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {
    public UserDO getUserName(Long userId) {
        return (UserDO) this.getSqlMapClientTemplate().queryForObject("user.queryById", userId);
    }
}
