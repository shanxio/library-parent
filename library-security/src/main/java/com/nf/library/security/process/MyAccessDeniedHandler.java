package com.nf.library.security.process;

import com.nf.library.utils.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限不足页面
 * @author Sam
 */

public class MyAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResponseVo responseVo = ResponseVo.builder()
                .code("403")
                .msg("权限不足")
                .data(accessDeniedException).build();
        JsonUtils.write(response.getOutputStream(),responseVo);
    }

}
