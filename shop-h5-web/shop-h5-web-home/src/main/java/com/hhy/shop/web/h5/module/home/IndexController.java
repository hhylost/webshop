package com.hhy.shop.web.h5.module.home;

import com.hhy.shop.common.utils.synchronous.AbstractTask;
import com.hhy.shop.common.utils.synchronous.AsyncTaskTrigger;
import com.hhy.shop.common.utils.synchronous.Task;
import com.hhy.shop.common.utils.synchronous.impl.AsyncTaskTriggerImpl;
import com.hhy.shop.web.h5.module.home.listener.RequestFeedback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2017/1/19.
 */
@Controller
public class IndexController {
    private static final Log LOGGER = LogFactory.getLog(IndexController.class.getClass());
    @RequestMapping("/")
    public String index(ModelMap model) {
        AsyncTaskTrigger asyncTaskTrigger = new AsyncTaskTriggerImpl();
        Task task = new AbstractTask() {
            @Override
            public Object doTask() throws Exception {
                new Thread().sleep(3000);
                LOGGER.error("3秒后打印数据啦！");
                return Thread.currentThread().getName();
            }
        };
        task.addLinstener(new RequestFeedback());
        asyncTaskTrigger.launch(task);
        model.put("name", "何海洋");
        return "index.ftl";
    }
}
