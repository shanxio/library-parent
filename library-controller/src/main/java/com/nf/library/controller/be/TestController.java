package com.nf.library.controller.be;


import com.nf.library.execption.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {
    @GetMapping("/test")
    public void te(String msg,HttpSession session){
        System.out.println(session.getAttribute("captcha"));
    }
}
