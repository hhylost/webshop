package com.hhy.shop.common.utils.synchronous;

/**
 * Created by lenovo on 2017/3/23.
 */
public interface Task<R> extends Listenable<R>{

    /**
     * 任务执行
     */
    R process();
}
