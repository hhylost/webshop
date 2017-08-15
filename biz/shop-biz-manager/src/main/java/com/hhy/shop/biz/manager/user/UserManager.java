package com.hhy.shop.biz.manager.user;

import com.hhy.shop.biz.dal.user.UserDAO;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;

/**
 * Created by lenovo on 2016/11/8.
 */
public interface UserManager {
    UserDO getUserName(Long userId);
    Long insert(UserDO userDO);
    UserDO getUserByUserId(Long userId);

    
}
