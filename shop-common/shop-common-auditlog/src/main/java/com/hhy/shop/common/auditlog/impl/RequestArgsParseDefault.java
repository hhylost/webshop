package com.hhy.shop.common.auditlog.impl;

import com.hhy.shop.common.auditlog.process.RequestArgsParse;

/**
 * @author hehy
 * @create 2017-03-28
 **/
public class RequestArgsParseDefault implements RequestArgsParse{
    public String parse() {
        return "默认解析器";
    }
}
