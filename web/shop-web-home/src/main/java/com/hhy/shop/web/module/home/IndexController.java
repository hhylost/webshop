package com.hhy.shop.web.module.home;

import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.manager.user.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author hehy
 * @desc 网站首页
 * @data 2016/11/08
 */
@Controller
public class IndexController {
    @Resource
    private UserManager userManager;
    /**
     * 网站首页
     */
    @RequestMapping("/")
    public String index(Model model, @RequestParam(value = "userId") Long userId){
        UserDO userDO = userManager.getUserName(userId);
        model.addAttribute("name", userDO.getUsername());
        return "index.ftl";
    }

    /**
     * 网站首页
     */
    @RequestMapping("/index2")
    public String index_home(Model model){
        model.addAttribute("name", "海宝宝");
        return "index.ftl";
    }
}
