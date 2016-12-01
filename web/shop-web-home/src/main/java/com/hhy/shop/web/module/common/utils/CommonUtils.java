package com.hhy.shop.web.module.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lenovo on 2016/11/15.
 */
public class CommonUtils {
    /**
     * 获取域名的三级域名，用于判断第三方应用 如百度轻应用：http://baidu.shop.com
     *
     * @param request
     * @return
     */
    public static String getThreeLevelDomain(HttpServletRequest request) {
        String domain = request.getServerName().toLowerCase();
        if (domain.indexOf(".") > 0) {
            return domain.substring(0, domain.indexOf("."));
        }
        return null;
    }
}
