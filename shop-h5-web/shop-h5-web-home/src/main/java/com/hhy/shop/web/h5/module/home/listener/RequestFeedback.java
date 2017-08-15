package com.hhy.shop.web.h5.module.home.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hhy.shop.common.utils.synchronous.Listener;

/**
 * @author hehy
 * @create 2017-03-24
 **/
public class RequestFeedback implements Listener{
    private static final Log LOGGER = LogFactory.getLog(RequestFeedback.class.getClass());
    public void callback(Object result) {
        LOGGER.error("反馈信息"+result);
    }
}
