package com.nf.library.controller.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nf.library.security.config.EnableSecurity;
import com.nf.library.service.config.EnableServiceSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * springmvc所需的配置
 * @author Sam
 */
@Configuration
@ComponentScan({"com.nf.library.controller.be","com.nf.library.controller.advice"})
@EnableSecurity
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

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver standardServletMultipartResolver(){
        StandardServletMultipartResolver standardServletMultipartResolver = new StandardServletMultipartResolver();
        return standardServletMultipartResolver;
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addExposedHeader("Authorization");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new org.springframework.format.datetime.DateFormatter("yyyy-MM-dd"));
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (int i = 0; i < converters.size(); i++) {
            HttpMessageConverter<?> messageConverter = converters.get(i);
            if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                converters.remove(i);
            }
        }
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        // 设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return mappingJackson2HttpMessageConverter;
    }
}
