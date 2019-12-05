package com.nf.library.security.process;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.utils.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseVo responseVo = ResponseVo.builder()
                .code("200")
                .msg("登录成功")
                .date("true").build();
        JsonUtils.write(response.getOutputStream(),responseVo);
    }
}
