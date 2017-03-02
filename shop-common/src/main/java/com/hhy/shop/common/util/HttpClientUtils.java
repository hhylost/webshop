package com.hhy.shop.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

/**
 * Http请求工具类
 *
 * @author hehy
 * @create 2017-03-01
 **/
public class HttpClientUtils {
    private static Logger LOGGER = Logger.getLogger(HttpClientUtils.class.getName());

    public static String URLEncode(String text, String charset) {
        if (StringUtils.isBlank(text)) {
            return "";
        } else {
            if (StringUtils.isBlank(charset)) {
                charset = "UTF-8";
            }

            String encoded = text;

            try {
                encoded = URLEncoder.encode(text, charset);
            } catch (UnsupportedEncodingException var4) {
                LOGGER.info("url编码异常，原始内容【" + text + "】" + var4);
            }

            return encoded;
        }
    }
}
