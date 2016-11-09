package com.hhy.shop.web.module.home.user;

import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.manager.user.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2016/11/9.
 */
@Controller
public class UserController {
    @Resource
    private UserManager userManager;
    /**
     * 网站首页
     */
    @RequestMapping("/userinfo")
    public String index(Model model, @RequestParam(value = "userId") Long userId,
                        @RequestParam(value = "username") String username) {
        UserDO userDO = userManager.getUserName(userId);
        model.addAttribute("name", userDO.getUsername());
        model.addAttribute("username", username);
        return "user/user.ftl";
    }
}
