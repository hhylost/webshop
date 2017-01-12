package com.hhy.shop.web.module.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hehy
 * @desc 网站首页
 * @data 2016/11/08
 */
@Controller
public class IndexController {

    /**
     * 网站首页
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "何海洋");
        return "index.ftl";
    }

    /**
     * 网站首页2
     */
    @RequestMapping("/index2")
    public String index_home(Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis()+"");//创建一个cookie，cookie的名字是lastAccessTime
        response.addCookie(cookie);
        model.addAttribute("name", "海宝宝");
        return "index.ftl";
    }
}
