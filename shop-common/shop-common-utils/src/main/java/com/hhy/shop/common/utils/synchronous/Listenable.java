package com.hhy.shop.common.utils.synchronous;

import java.util.Set;

/**
 * Created by lenovo on 2017/3/24.
 */
public interface Listenable<R> {
    /*
     * 任务执行完毕回调Listener
     */
    public void notiyListener(R result);

    public void addLinstener(Listener<R> listener);

    public void addAllLinstener(Set<Listener<R>> listeners);

    public void removeLinstener(Listener<R> listener);

    public void removeAllLinstener();
}
