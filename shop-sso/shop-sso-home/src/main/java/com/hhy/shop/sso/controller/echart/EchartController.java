package com.hhy.shop.sso.controller.echart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hehy
 * @create 2017-03-27
 **/
@Controller
@RequestMapping("/echart")
public class EchartController {
    @RequestMapping("/show")
    public String echart(){
        return "login/echart";
    }
}
