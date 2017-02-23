package com.hhy.shop.sso.controller.common.utils;

import com.hhy.shop.sso.controller.common.constants.GlobalConstants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 常用工具类
 *
 * @author hehy
 * @create 2017-02-23
 **/
public class CommonUtils {
    /**
     * 获得端口号
     *
     * @param request
     * @return String
     */
    public static String getServerPort(HttpServletRequest request) {
        int serverport = request.getServerPort();
        if (serverport != 80 && serverport != 443) {
            return ":" + serverport;
        }
        return StringUtils.EMPTY;
    }

    public static String getServerName(HttpServletRequest request) {
        return request.getServerName();
    }

    public static String getServerNameAndPort(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + getServerPort(request);
    }

    public static String getServerNameAndPortWithoutSchema(HttpServletRequest request) {
        return request.getServerName() + getServerPort(request);
    }
    /**
     * 根据request.getScheme来处理请求Url中到底是Http还是Https
     *
     * @param server
     * @param scheme
     * @return
     */
    public static String switchHttpAndHttps(String server, String scheme) {
        if (StringUtils.isBlank(server)) {
            return server;
        }
        if (GlobalConstants.REQUEST_SCHEME_HTTP.equalsIgnoreCase(scheme)
                && StringUtils.startsWith(server, GlobalConstants.REQUEST_SCHEME_HTTPS)) {
            return StringUtils.replaceOnce(server, GlobalConstants.REQUEST_SCHEME_HTTPS, scheme);
        } else if (GlobalConstants.REQUEST_SCHEME_HTTPS.equalsIgnoreCase(scheme)
                && !StringUtils.startsWith(server, GlobalConstants.REQUEST_SCHEME_HTTPS)) {
            return StringUtils.replaceOnce(server, GlobalConstants.REQUEST_SCHEME_HTTP, scheme);
        }
        return server;
    }
}
