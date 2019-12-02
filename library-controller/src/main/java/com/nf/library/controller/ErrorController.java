package com.nf.library.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sam
 */
@Controller
public class ErrorController {

    @RequestMapping("403")
    public String  forbidden(){
        return "403";
    }
}
