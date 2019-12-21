package com.nf.library.controller.be.bookInfo;

import com.nf.library.entity.BookType;
import com.nf.library.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/type")
public class BookTypeController {

    @Autowired
    private BookTypeService bookTypeService;

    @RequestMapping("/getAll")
    public List<BookType> getAll(){
        return  bookTypeService.getAll();
    }
}
