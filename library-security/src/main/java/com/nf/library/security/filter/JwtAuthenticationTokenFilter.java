package com.nf.library.security.filter;

import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.impl.UserDetailsServiceImpl;
import com.nf.library.utils.JsonUtils;
import com.nf.library.utils.JwtResult;
import com.nf.library.utils.JwtSubject;
import com.nf.library.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用于jwt认证的过滤器
 * @author Sam
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final  String JWT_TOKEN = "token";
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeard = request.getHeader(JWT_TOKEN);
        response.setContentType("application/json;charset=utf-8");
        if(request.getRequestURI().contains("img")){
            filterChain.doFilter(request,response);
            return;
        }
        if(!"/userLogin".equals(request.getRequestURI())&&!"/captcha.jpg".equals(request.getRequestURI())){
            ResponseVo responseVo = null;
            if(authHeard!=null){

                JwtResult jwtResult = JwtUtils.validateJWT(authHeard);
                if(jwtResult.getSuccess()){
                    //验证成功的负载
                    String subject = jwtResult.getClaims().getSubject();
                    //将传过来的值转为subject，
                    JwtSubject jwtSubject = JsonUtils.readValue(subject,JwtSubject.class);
                    String username = jwtSubject.getUsername();
                    log.info("登录用户名称为："+username);
                    if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }else{
                    if(JwtUtils.JWT_ERRCOOE_EXPIRE.equals(jwtResult.getErrCode())){
                        responseVo = ResponseVo.builder()
                                .code(jwtResult.getErrCode())
                                .msg("登录时间已超时，请重新登录").build();
                    }else{
                        responseVo = ResponseVo.builder()
                                .code(jwtResult.getErrCode())
                                .msg("验证不通过请重新登录，请重新登录").build();
                    }
                    JsonUtils.write(response.getOutputStream(),responseVo);
                    return;
                }

            }else{
                responseVo = ResponseVo.builder()
                        .code("401")
                        .msg("没有登录").build();
                JsonUtils.write(response.getOutputStream(),responseVo);
                return;
            }
        }

        filterChain.doFilter(request,response);
    }
}
