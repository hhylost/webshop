package com.hhy.shop.common.utils.synchronous;

/**
 * Created by lenovo on 2017/3/23.
 */
public interface Listener<R> {
    void callback(R result);
}
