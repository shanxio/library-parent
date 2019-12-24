package com.nf.library.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerifcationCodeFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
//    @CrossOrigin(allowCredentials = "true")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!"/userLogin".equals(request.getRequestURI())){
            filterChain.doFilter(request,response);
        }else{
            try {
                verificationCode(request);
                filterChain.doFilter(request,response);
            } catch (VerficationAuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
            }
        }
    }

    public void verificationCode(HttpServletRequest request) throws AuthenticationException {
        String requestCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String savedCode = (String) session.getAttribute("captcha");
        //校验不通过，抛出异常
        if(StringUtils.isEmpty(requestCode)|| StringUtils.isEmpty(savedCode)||!requestCode.equals(savedCode)){
            throw new VerficationAuthenticationException("图形验证码校验失败");
        }
        if(!StringUtils.isEmpty(savedCode)){
            //清除验证码
            session.removeAttribute("captcha");
        }
    }
}
