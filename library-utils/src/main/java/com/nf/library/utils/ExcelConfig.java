package com.nf.library.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel配置注解
 * @author Sam
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelConfig {
    /**
     * 导入时，对应实体的字段 主要是用户区分每个字段，
     * @return 列名
     */
     String value() default "id";
}
