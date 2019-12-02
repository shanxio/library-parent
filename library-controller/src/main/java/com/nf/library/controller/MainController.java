package com.nf.library.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sam
 */
@Controller
public class MainController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
