package com.hhy.shop.sso.controller.auditlog.parse;

import com.hhy.shop.common.auditlog.process.RequestArgsParse;

/**
 * @author hehy
 * @create 2017-03-28
 **/
public class RequestArgsParseSSO implements RequestArgsParse{
    public String parse() {
        return "sso解析器";
    }
}
