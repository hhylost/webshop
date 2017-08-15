package com.hhy.shop.common.utils.synchronous.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hhy.shop.common.utils.synchronous.AsyncTaskTrigger;
import com.hhy.shop.common.utils.synchronous.Task;
import com.hhy.shop.common.utils.synchronous.impl.invoker.TaskInvoker;

/**
 * @author hehy
 * @create 2017-03-24
 **/
public class AsyncTaskTriggerImpl implements AsyncTaskTrigger {
    private static final ExecutorService threadPool = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 60L,
            TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    @Override
    public <R> Future<R> launch(Task<R> task) {
        TaskInvoker<R> taskInvoker = new TaskInvoker<R>(task);
        return threadPool.submit(taskInvoker);
    }
}
