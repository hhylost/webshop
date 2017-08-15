package com.hhy.shop.common.utils.synchronous.impl.invoker;

import com.hhy.shop.common.utils.synchronous.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.Callable;

/**
 * @author hehy
 * @create 2017-03-24
 **/
public class TaskInvoker<R> implements Callable<R>{
    private static final Log LOGGER = LogFactory.getLog(TaskInvoker.class);

    private final Task<R> task;

    public TaskInvoker(Task<R> task){
        this.task=task;
    }
    @Override
    public R call() throws Exception {
        if(null == task){
            throw new NullPointerException("task can not be null");
        }
        LOGGER.info("task begin");
        R obj = task.process();
        LOGGER.info("task end");
        return obj;
    }
}
