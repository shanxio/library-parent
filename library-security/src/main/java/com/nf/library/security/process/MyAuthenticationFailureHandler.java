package com.nf.library.security.process;

import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录错误处理
 * @author Sam
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
//        // 允许跨域
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        // 允许自定义请求头token(允许head跨域)
//        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        response.setContentType("application/json;charset=utf-8");
        ResponseVo responseVo = ResponseVo.builder()
                .code("400")
                .msg("登录失败")
                .data(exception).build();
        JsonUtils.write(response.getOutputStream(),responseVo);
    }
}
