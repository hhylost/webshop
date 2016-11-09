package com.hhy.shop.web.module.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String index_home(Model model) {
        model.addAttribute("name", "海宝宝");
        return "index.ftl";
    }
}
