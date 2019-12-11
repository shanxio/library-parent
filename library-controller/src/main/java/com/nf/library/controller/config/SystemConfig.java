package com.nf.library.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

/**
 * 此类用来初始化springmvc的。
 * @author Sam
 */
public class SystemConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);
        ServletRegistration.Dynamic registration =
                (ServletRegistration.Dynamic) servletContext.getServletRegistration(getServletName());
        registration.setMultipartConfig(new MultipartConfigElement(servletContext.getRealPath("/file")));

    }



}
