package com.hhy.shop.web.module.common.json;

import java.util.Map;

/**
 * @author hehy
 * @desc json
 * @data 2016/11/15
 */
public class BaseJsonObject {

    private boolean hasError = false;

    /**
     * code=500时表示当前请求的操作出错了 需要转到当前操作失败页 1表示成功
     */
    private String code = "1";

    private Map<Object, Object> map;

    private String returnUrl;

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
