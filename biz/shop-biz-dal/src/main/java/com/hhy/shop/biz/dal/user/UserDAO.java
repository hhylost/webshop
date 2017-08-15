package com.hhy.shop.biz.dal.user;

import com.hhy.shop.biz.dal.base.PageQuery;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.dal.user.query.UserPageQuery;

/**
 * Created by lenovo on 2016/10/17.
 */
public interface UserDAO {
    UserDO getUserName(Long userId);

    Long insert(UserDO userDO);

    PageQuery<UserDO> findByQueryCondition(UserPageQuery userPageQuery);
}
