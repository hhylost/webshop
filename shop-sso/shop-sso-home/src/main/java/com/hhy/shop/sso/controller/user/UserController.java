package com.hhy.shop.sso.controller.user;

import com.hhy.shop.common.auditlog.annotation.AuditLogAnnotation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hhy.shop.common.utils.tools.HttpClientUtils;

/**
 * 用户登录控制器
 *
 * @author hehy
 * @create 2017-02-21
 **/
@Controller
public class UserController {

    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    @AuditLogAnnotation(operateDetail = "用户登录操作", operateName = "用户登录操作名称")
    public String login(ModelMap model , @RequestParam(value = "target", required = false) String target){
        if(StringUtils.isBlank(target)){
            target="/";
        }
        target = HttpClientUtils.URLEncode(target, "UTF-8");
        model.put("target", target);
        model.put("test", null);
        return "login/login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String userLogin(){
        return "login/index";
    }
}
