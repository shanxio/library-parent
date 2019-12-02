package com.nf.library.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试所用的控制器
 * @author Sam
 */
@RestController
public class TestController {

    @RequestMapping("/test1")
    public String test1(){
        return "test1------------";
    }

    @RequestMapping("/test2")
    public String test2(){
        return "test2-------------";
    }
}
