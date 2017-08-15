package com.hhy.shop.common.utils.synchronous;

import java.util.concurrent.Future;

/**
 * Created by lenovo on 2017/3/24.
 */
public interface AsyncTaskTrigger {
    <R> Future<R> launch(Task<R> task);
}
