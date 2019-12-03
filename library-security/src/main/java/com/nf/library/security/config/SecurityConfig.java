package com.nf.library.security.config;


import com.nf.library.security.authorize.AuthorizeConfigManager;
import com.nf.library.security.process.MyAccessDeniedHandler;
import com.nf.library.security.process.MyAuthenticationFailureHandler;
import com.nf.library.security.process.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * 用于securit各种安全配置
 * @author Sam
 */
@EnableWebSecurity
@ComponentScan({"com.nf.library.security.process","com.nf.library.security.authorize"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        authorizeConfigManager.config(http.authorizeRequests());
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/userLogin")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler());

                //自定义异常处理
                http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler("/403"));
                http.csrf().disable();
                //http.authorizeRequests().anyRequest().authenticated();
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
