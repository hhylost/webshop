package com.hhy.shop.sso.controller.user;

import com.hhy.shop.common.util.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * 用户登录控制器
 *
 * @author hehy
 * @create 2017-02-21
 **/
@Controller
public class UserController {
    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public String login(ModelMap model , @RequestParam(value = "target", required = false) String target){
        if(StringUtils.isBlank(target)){
            target="/";
        }
        target = HttpClientUtils.URLEncode(target, "UTF-8");
        model.put("target", target);
        return "login/login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String userLogin(){
        return "login/index";
    }
}
