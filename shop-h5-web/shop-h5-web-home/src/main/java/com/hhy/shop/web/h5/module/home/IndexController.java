package com.hhy.shop.web.h5.module.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2017/1/19.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(ModelMap model) {
        model.put("name", "何海洋");
        return "index.ftl";
    }
}
