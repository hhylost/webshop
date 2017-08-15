package com.hhy.shop.common.auditlog;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.manager.user.UserManager;
import com.hhy.shop.common.auditlog.bo.AuditLogBO;
import com.hhy.shop.common.utils.synchronous.AsyncTaskTrigger;
import com.hhy.shop.common.utils.synchronous.impl.AsyncTaskTriggerImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.hhy.shop.common.auditlog.annotation.AuditLogAnnotation;
import com.hhy.shop.common.auditlog.impl.RequestArgsParseConfigurer;
import com.hhy.shop.common.auditlog.impl.RequestArgsParseDefault;
import com.hhy.shop.common.auditlog.process.RequestArgsParse;
import com.hhy.shop.common.utils.synchronous.AbstractTask;
import com.hhy.shop.common.utils.synchronous.Task;

/**
 * @author hehy
 * @create 2017-03-22
 **/
@Aspect
public class AuditLogAspect {
    private static final Log LOGGER = LogFactory.getLog(AuditLogAspect.class.getClass());

    public AuditLogAspect(){
        LOGGER.error("AuditLogAspect被初始化了");
    }

    @Resource
    private UserManager userManager;
    @Resource
    private RequestArgsParseConfigurer requestArgsParseConfigurer;

    private RequestArgsParse requestArgsParse;
    AsyncTaskTrigger trigger = new AsyncTaskTriggerImpl();

    @Pointcut(" @annotation( com.hhy.shop.common.auditlog.annotation.AuditLogAnnotation ) ")
    public void auditAspect() {
        // 定义切面，有这个注解的就进行代理
    }

    @AfterReturning(returning = "ret", value = "auditAspect()")
    public void doAfter(final JoinPoint joinPoint, final Object ret){
        final AuditLogBO auditLogBO = new AuditLogBO();
        String params = StringUtils.EMPTY;
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AuditLogAnnotation auditLogAnnotion = method.getAnnotation(AuditLogAnnotation.class);
        String operateName = auditLogAnnotion.operateName();
        if(requestArgsParseConfigurer.getRequertArgsParse2CfgMap().containsKey(operateName)){
            requestArgsParse = requestArgsParseConfigurer.getRequertArgsParse2CfgMap().get(operateName);
        }else {
            requestArgsParse = new RequestArgsParseDefault();
        }
        if(null != requestArgsParse){
            params = requestArgsParse.parse();
        }
        final UserDO userDO = new UserDO();
        userDO.setUsername("audit");
        userDO.setPassword("aaaa");
        userDO.setEmail("www.baidu.com");
        LOGGER.error("切面执行1");
        auditLogBO.setOperateDetail(params);
        Task task = new AbstractTask() {
            @Override
            public Object doTask() throws Exception {
                userManager.insert(userDO);
                Thread.sleep(3000);
                LOGGER.error(auditLogBO.getOperateDetail());
                LOGGER.error("切面执行");
                return auditLogBO;
            }
        };
        trigger.launch(task);
    }

}
