package com.hhy.shop.biz.manager.user.impl;

import com.hhy.shop.biz.dal.user.UserDAO;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.manager.user.UserManager;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2016/11/8.
 */
public class UserManagerImpl implements UserManager{
    @Resource
    private UserDAO userdao;

    @Override
    public UserDO getUserName(Long userId){
        return userdao.getUserName(userId);
    }
}
