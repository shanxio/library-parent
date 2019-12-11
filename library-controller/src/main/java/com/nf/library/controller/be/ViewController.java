package com.nf.library.controller.be;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 所有的视图转发
 * @author Sam
 */
@Controller
public class ViewController {

    @RequestMapping("/admin/bookInfo/bookView")
    public String bookView(){
        return "be/book";
    }

    @RequestMapping("/index")
    public String index(){
        return "be/index";
    }
}
