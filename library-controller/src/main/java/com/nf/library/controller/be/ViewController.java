package com.nf.library.controller.be;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/bookInfo")
public class ViewController {
    @RequestMapping("/bookView")
    public String bookView(){
        return "be/bookInfo";
    }
}
