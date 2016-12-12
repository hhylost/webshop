package com.hhy.shop.user.service.share;

import com.hhy.shop.user.service.dto.UserDTO;
import com.hhy.shop.user.service.share.base.ResponseDTO;

/**
 * Created by lenovo on 2016/12/12.
 */
public interface UserService {
    ResponseDTO<UserDTO> getUserByUserId(Long userId);
}
