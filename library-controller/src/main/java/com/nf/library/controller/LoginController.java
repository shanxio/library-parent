package com.nf.library.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *处理登录的一系列请求
 * @author Sam
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginView(){

        return "login";
    }

}
