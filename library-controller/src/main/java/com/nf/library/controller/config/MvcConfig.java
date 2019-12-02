package com.nf.library.controller.config;


import com.nf.library.security.config.EnableSecurit;
import com.nf.library.service.config.EnableServiceSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc所需的配置
 * @author Sam
 */
@Configuration
@ComponentScan({"com.nf.library.controller"})
@EnableServiceSpring
@EnableSecurit
@EnableWebMvc
public class MvcConfig  implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        return internalResourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration registration =
                registry.addResourceHandler("/static/**");
        registration.addResourceLocations("classpath:/static/");
    }
}
