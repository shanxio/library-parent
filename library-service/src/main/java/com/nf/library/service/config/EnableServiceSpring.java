package com.nf.library.service.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 用于service的注解
 * @author Sam
 */
@Configuration
@Target({ ElementType.TYPE })
@Retention( RetentionPolicy.RUNTIME )
@Import(ServiceConfig.class)
public @interface EnableServiceSpring {
}
