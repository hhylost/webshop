package com.hhy.shop.biz.manager.user.impl;

import javax.annotation.Resource;

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
    private UserService userService;

    @Override
    public UserDO getUserName(Long userId) {
        UserDO userDO = new UserDO();
        ResponseDTO<UserDTO> result = userService.getUserByUserId(userId);
        if (null == result) {
            LOGGER.error("调用服务错误！");
            return null;
        }
        if (null != result.getData()) {
            UserDTO userDTO = result.getData();
            BeanUtils.copyProperties(userDTO, userDO);
        } else {
            LOGGER.error(result.getMessage());
            return null;
        }
        return userDO;
    }
}
