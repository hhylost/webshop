package com.hhy.shop.biz.manager.user.impl;

import javax.annotation.Resource;

import com.hhy.shop.biz.dal.user.UserDAO;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.manager.user.UserManager;
import com.hhy.shop.user.service.dto.UserDTO;
import com.hhy.shop.user.service.share.UserService;
import com.hhy.shop.user.service.share.base.ResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

/**
 * Created by lenovo on 2016/11/8.
 */
public class UserManagerImpl implements UserManager {
    private static final Log LOGGER = LogFactory.getLog(UserManagerImpl.class);
    @Resource
    private UserDAO userDAO;

    @Override
    public UserDO getUserName(Long userId) {
        UserDO userDO = userDAO.getUserName(userId);
        if (null == userDO) {
            LOGGER.error("获取用户信息出错");
            return null;
        }
        return userDO;
    }

    @Override
    public Long insert(UserDO userDO) {
        return userDAO.insert(userDO);
    }

    @Override
    public UserDO getUserByUserId(Long userId) {
        return userDAO.getUserName(userId);
    }


}
