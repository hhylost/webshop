package com.hhy.shop.web.module.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2016/11/8.
 */
@Controller
public class ErrorController {

    @RequestMapping("/notfound")
    public String error404(Model model){
        model.addAttribute("errorMessage", "页面不存在！");
        return "error/404.ftl";
    }
}
