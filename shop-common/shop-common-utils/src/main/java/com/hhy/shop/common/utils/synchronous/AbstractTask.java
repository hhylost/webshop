package com.hhy.shop.common.utils.synchronous;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author hehy
 * @create 2017-03-24
 **/
public abstract class AbstractTask<R> implements Task<R> {
    private static final Log LOGGER = LogFactory.getLog(AbstractTask.class.getClass());
    private Set<Listener<R>> listeners = new HashSet<Listener<R>>();

    @Override
    public R process() {
        R result = null;
        try {
            result = doTask();
        } catch (Exception e) {
            LOGGER.error("异步任务执行失败：" , e);
        }
        notiyListener(result);
        return null;
    }

    @Override
    public void notiyListener(R result) {
        if(CollectionUtils.isNotEmpty(listeners)){
            for(Listener<R> listener : listeners){
                listener.callback(result);
            }
        }
    }

    @Override
    public void addLinstener(Listener<R> listener) {
        listeners.add(listener);
    }

    @Override
    public void addAllLinstener(Set<Listener<R>> listeners) {
        listeners.addAll(listeners);
    }

    @Override
    public void removeLinstener(Listener<R> listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeAllLinstener() {
        listeners.clear();
    }

    public abstract R doTask() throws Exception;
}
