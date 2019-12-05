package com.nf.library.controller.be;


import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试所用的控制器
 * @author Sam
 */
@Controller
@Log
public class TestController {
    @ResponseBody
    @RequestMapping("/test1")
    public String test1(){



        return "test1------------";
    }
    @ResponseBody
    @RequestMapping("/test2")
    public String test2(){
        return "test2-------------";
    }
    @RequestMapping("/test")
    public String test(){
        return "/be/test";
    }
}
