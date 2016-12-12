package com.hhy.shop.common.json;

import java.util.Map;

/**
 * Created by lenovo on 2016/12/12.
 */
public class ShopResult {

    // 状态码：0成功，1异常，500错误
    private Integer code = 0;

    // 结果信息
    private String message;

    // 查询结果是否有错
    private Boolean hasError = false;

    // 返回结果
    private Object data;

    // 返回结果map类型
    private Map<String, Object> map;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
