package com.hhy.shop.web.module.common.interecptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hhy.shop.web.module.common.utils.CommonUtils;

/**
 * @author hehy
 * @desc 域名拦截器
 * @data 2016/11/15
 */
public class DomainInterecptor extends HandlerInterceptorAdapter {
    private static final Log LOGGER = LogFactory.getLog(DomainInterecptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String domainProfix = CommonUtils.getThreeLevelDomain(request);
        LOGGER.error("日志处理！");
        LOGGER.error("域名：" + domainProfix);
        return true;
    }

}
