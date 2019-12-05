package com.nf.library.controller.be.bookInfo;


import com.github.pagehelper.PageInfo;
import com.nf.library.controller.vo.BookInfoVo;
import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.BookInfoService;
import com.nf.library.service.impl.BookInfoServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * 关于书籍信息表的操作
 * @author Sam
 */
@RestController
@RequestMapping("/admin/bookInfo")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    @PostMapping("/getAll")
    public PageInfo<BookInfo> getAll(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize){
        List<BookInfo> bookInfos = bookInfoService.getAll(pageNum,pageSize);
        PageInfo<BookInfo> pageInfos = new PageInfo<>(bookInfos,pageSize);
        return pageInfos;
    }


    @PostMapping("/bookInfoInsert")
    public ResponseVo bookInfoInsert(@Valid BookInfoVo bookInfoVo, BindingResult bindingResult){
        ResponseVo responseVo = null;
        if(bindingResult.hasErrors()){
            responseVo = ResponseVo.builder().code("205").msg("添加失败").date(bindingResult).build();
        }else{
            BookInfo bookInfo = new BookInfo();
            BeanUtils.copyProperties(bookInfoVo,bookInfo);
            bookInfoService.bookInfoInsert(bookInfo);
            responseVo = ResponseVo.builder().code("200").msg("添加成功").build();
        }
        return responseVo;
    }


    @GetMapping("bookInfoDeleteById")
    public ResponseVo bookInfoInsert(Integer id){
        ResponseVo responseVo = null;
        try{
            bookInfoService.bookInfoDeleteById(id);
            responseVo = ResponseVo.builder().code("200").msg("删除成功").build();
        }catch (RuntimeException e){
            throw new AppException("删除失败",e);
        }
        return responseVo;
    }

}
