package com.hhy.shop.sso.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户登录控制器
 *
 * @author hehy
 * @create 2017-02-21
 **/
@Controller
public class UserController {
    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public String login(){
        return "login/login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String userLogin(){
        return "login/index";
    }
}
