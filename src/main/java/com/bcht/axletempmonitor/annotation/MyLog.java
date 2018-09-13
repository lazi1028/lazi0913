package com.bcht.axletempmonitor.annotation;

import java.lang.annotation.*;
/**
 * 自定义日志注解
 *
 * Target 注解放置的目标位置 method 是方法级别的注解
 * Retention 注解在运行时执行
 * Documented 生成文档
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}
