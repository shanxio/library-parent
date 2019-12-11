package com.nf.library.security.process;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.utils.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义成功处理
 * @author Sam
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers",
                "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        response.setContentType("application/json;charset=utf-8");
        ResponseVo responseVo = null;
        //获取用户信息
            responseVo = ResponseVo.builder()
                    .code("200")
                    .msg("登录成功")
                    .data("").build();

        JsonUtils.write(response.getOutputStream(),responseVo);
    }
}
