package com.nf.library.controller.be;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.util.Debug;

/**
 *处理登录的一系列请求
 * @author Sam
 */
@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String loginView(){
        log.info("------------------------------");
        return "login";
    }

}
