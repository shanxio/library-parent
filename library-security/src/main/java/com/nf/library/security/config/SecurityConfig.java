package com.nf.library.service.config;


import com.nf.library.security.process.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
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
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
                http.csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

   @Bean
    public UserDetailsManager inMemoryUserDetailsManager(){
       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
       manager.createUser(User.withUsername("admin").password("$2a$10$k9zX2LRoVlSgHWo6GbU1VubWD.FPRias9FH1A8MRTH0heSb9BqQny").roles("ADMIN").build());
       return manager;
   }
}
