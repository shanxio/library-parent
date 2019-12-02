package com.nf.library.dao.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * mybatis与spring的整合注解。
 * @author Sam
 */
@Configuration
@Target({ ElementType.TYPE })
@Retention( RetentionPolicy.RUNTIME )
@Import(DaoConfig.class)
public @interface EnableMybatisSpring {
}
