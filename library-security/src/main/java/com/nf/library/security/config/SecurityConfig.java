package com.nf.library.security.config;


import com.nf.library.security.authorize.AuthorizeConfigManager;
import com.nf.library.security.filter.JwtAuthenticationTokenFilter;
import com.nf.library.security.filter.VerifcationCodeFilter;
import com.nf.library.security.process.MyAccessDeniedHandler;
import com.nf.library.security.process.MyAuthenticationFailureHandler;
import com.nf.library.security.process.MyAuthenticationSuccessHandler;
import com.nf.library.service.NodeInfoService;
import com.nf.library.service.config.EnableServiceSpring;
import com.nf.library.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于securit各种安全配置
 * @author Sam
 */
@Configuration
@EnableWebSecurity
@ComponentScan({"com.nf.library.security.process",
        "com.nf.library.security.authorize",
        "com.nf.library.security.filter"})
@EnableServiceSpring
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    @Autowired
    private VerifcationCodeFilter verifcationCodeFilter;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

   

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().configurationSource(configurationSource()).and().csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
        http.formLogin()
                .loginProcessingUrl("/userLogin")
                .successHandler(successHandler)
                .failureHandler(failureHandler);
                //不要创建会话
                //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //添加jwt认证过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(verifcationCodeFilter, JwtAuthenticationTokenFilter.class);
        //自定义异常处理
       http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
        //禁用页面缓存
       http.headers().cacheControl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedOrigin("http://127.0.0.1:8081");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("HEAD");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
//       @Bean
//    public UserDetailsManager inMemoryUserDetailsManager(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin").password("$2a$10$k9zX2LRoVlSgHWo6GbU1VubWD.FPRias9FH1A8MRTH0heSb9BqQny").roles("ADMIN").build());
//        return manager;
//    }
}
