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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

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
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private NodeInfoService nodeInfoService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        authorizeConfigManager.config(http.authorizeRequests());
        http.formLogin()
                .loginProcessingUrl("/userLogin")
                .successHandler(new MyAuthenticationSuccessHandler(nodeInfoService))
                .failureHandler(new MyAuthenticationFailureHandler());
                //不要创建会话
                //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        //添加jwt认证过滤器
        http.addFilterBefore(new JwtAuthenticationTokenFilter(userDetailsService),
                UsernamePasswordAuthenticationFilter.class);

        //http.addFilterBefore(new VerifcationCodeFilter(), JwtAuthenticationTokenFilter.class);
        //自定义异常处理
                http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
                //禁用页面缓存
                http.headers().cacheControl();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    //   @Bean
//    public UserDetailsManager inMemoryUserDetailsManager(){
//       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//       manager.createUser(User.withUsername("admin").password("$2a$10$k9zX2LRoVlSgHWo6GbU1VubWD.FPRias9FH1A8MRTH0heSb9BqQny").roles("ADMIN").build());
//       return manager;
//   }
}
