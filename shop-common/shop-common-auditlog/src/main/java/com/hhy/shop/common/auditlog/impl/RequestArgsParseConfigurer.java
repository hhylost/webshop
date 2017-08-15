package com.hhy.shop.common.auditlog.impl;

import com.hhy.shop.common.auditlog.process.RequestArgsParse;

import java.util.Map;

/**
 * @author hehy
 * @create 2017-03-28
 **/
public class RequestArgsParseConfigurer {
    private Map<String, RequestArgsParse> requertArgsParse2CfgMap;

    public Map<String, RequestArgsParse> getRequertArgsParse2CfgMap() {
        return requertArgsParse2CfgMap;
    }

    public void setRequertArgsParse2CfgMap(Map<String, RequestArgsParse> requertArgsParse2CfgMap) {
        this.requertArgsParse2CfgMap = requertArgsParse2CfgMap;
    }
}
