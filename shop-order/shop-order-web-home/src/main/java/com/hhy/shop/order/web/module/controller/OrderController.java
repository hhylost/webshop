package com.hhy.shop.order.web.module.controller;


import com.hhy.shop.biz.dal.order.dataobject.TbOrderDO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2016/12/21.
 */
@RequestMapping(value = "/order", method = RequestMethod.POST)
public class OrderController {

    @RequestMapping("/create")
    @ResponseBody
    public String createOrder(@RequestBody TbOrderDO order){
        return null;
    }
}