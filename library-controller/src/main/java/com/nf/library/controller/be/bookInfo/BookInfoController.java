package com.nf.library.controller.be.bookInfo;


import com.github.pagehelper.PageInfo;
import com.nf.library.controller.vo.BookInfoPageVo;
import com.nf.library.controller.vo.BookInfoVo;
import com.nf.library.controller.vo.PageVo;
import com.nf.library.entity.BookInfo;
import com.nf.library.security.process.UserInfo;
import com.nf.library.execption.AppException;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.BookInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 *
 * 关于书籍信息表的操作
 * @author Sam
 */
@RestController
@RequestMapping("/admin/bookInfo")
public class BookInfoController extends BaseBookInfoController{

    @Autowired
    private BookInfoService bookInfoService;

    @RequestMapping("/getAll")
    public PageInfo<BookInfo> getAll(@RequestBody BookInfoPageVo bookInfo){
        List<BookInfo> bookInfos = bookInfoService.getSearchAll(bookInfo.getBookInfo(),bookInfo.getPageVo().getPageNum(),bookInfo.getPageVo().getPageSize());
        PageInfo<BookInfo> pageInfos = new PageInfo<>(bookInfos,bookInfo.getPageVo().getPageSize());
        return pageInfos;
    }
//
//    @RequestMapping("/test")
//    public List<BookInfo> getAll2(@RequestBody BookInfoPageVo bookInfo){
//        System.out.println(bookInfo);
//        List<BookInfo> bookInfos = bookInfoService.getAll(1,2);
//        return bookInfos;
//    }

    @RequestMapping("/getIsbn")
    public BookInfo getIsbn(String isbn){
      BookInfo bookInfo = bookInfoService.getByIsbn(isbn);
        return bookInfo;
    }
    @PostMapping("/bookInfoInsert")
    public ResponseVo bookInfoInsert(@Valid BookInfoVo bookInfoVo, BindingResult bindingResult){
        ResponseVo responseVo = null;
        if(bindingResult.hasErrors()){
            responseVo = ResponseVo.builder().code("205").msg("添加失败").data(bindingResult).build();
        }else{
            BookInfo bookInfo = new BookInfo();
            BeanUtils.copyProperties(bookInfoVo,bookInfo);
            bookInfoService.bookInfoInsert(bookInfo);
            responseVo = ResponseVo.builder().code("200").msg("添加成功").build();
        }
        return responseVo;
    }


    @PostMapping("/bookInfoUpdate")
    public ResponseVo bookInfoUpdate(@Valid BookInfoVo bookInfoVo,BindingResult bindingResult){
        this.validException(bindingResult);
        ResponseVo responseVo = null;
        BookInfo bookInfo = new BookInfo();
        BeanUtils.copyProperties(bookInfoVo,bookInfo);

        bookInfoService.bookInfoUpdate(bookInfo);
        responseVo = ResponseVo.builder().code("200").msg("修改成功").build();
        return responseVo;
    }

    @GetMapping("bookInfoDeleteById")
    public ResponseVo bookInfoInsert(Integer id){
        this.checkNull(id);
        ResponseVo responseVo = null;
        try{
            bookInfoService.bookInfoByIdDelete(id);
            responseVo = ResponseVo.builder().code("200").msg("删除成功").build();
        }catch (RuntimeException e){
            throw new AppException("删除失败",e);
        }
        return responseVo;
    }


    @PostMapping("bookInfoDeleteBatch")
    public ResponseVo bookInfoDeleteBatch(String[] isbns){
        this.checkNull(isbns);
        ResponseVo responseVo = null;
        try{

            bookInfoService.bookInfoBatchDelete(isbns);
            responseVo = ResponseVo.builder().code("200").msg("删除成功").build();
        }catch (RuntimeException e){
            throw new AppException("删除失败",e);
        }
        return responseVo;
    }

}
