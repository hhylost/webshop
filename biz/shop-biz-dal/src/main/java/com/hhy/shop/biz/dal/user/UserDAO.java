package com.hhy.shop.biz.dal.user;

import com.hhy.shop.biz.dal.user.dataobject.UserDO;

/**
 * Created by lenovo on 2016/10/17.
 */
public interface UserDAO {
    public UserDO getUserName(Long userId);
}
