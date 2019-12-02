package com.nf.library.security.config;


import com.nf.library.security.process.MyAccessDeniedHandler;
import com.nf.library.security.process.MyAuthenticationFailureHandler;
import com.nf.library.security.process.MyAuthenticationSuccessHandler;
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
@ComponentScan("com.nf.library.security.process")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/userLogin").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/userLogin")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler());
                /*测试代码*/
                http.authorizeRequests()
                        .antMatchers("/test1").hasAnyAuthority("ROLE_ADMIN")
                        .antMatchers("/test2").hasAnyAuthority("ROLE_DE");
                //自定义异常处理
                http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler("/403"));

                http.csrf().disable();
                http.authorizeRequests().anyRequest().authenticated();
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
