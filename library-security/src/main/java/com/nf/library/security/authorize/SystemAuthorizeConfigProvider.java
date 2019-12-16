package com.nf.library.security.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 系统授权配置
 * @author Sam
 */
@Component
@Order(Integer.MAX_VALUE)
public class SystemAuthorizeConfigProvider implements AuthorizeConfigProvider {


    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.anyRequest().
                access("@rbacService.hasPermission(request,authentication)");
        return true;
    }
}
