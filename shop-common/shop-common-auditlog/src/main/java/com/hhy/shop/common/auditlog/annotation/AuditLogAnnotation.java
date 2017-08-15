package com.hhy.shop.common.auditlog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lenovo on 2017/3/22.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLogAnnotation {

    /**
     * 操作名称
     */
    String operateName();

    /**
     * 操作详情
     */
    String operateDetail();
}
