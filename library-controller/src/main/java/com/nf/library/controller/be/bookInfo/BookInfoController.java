package com.nf.library.controller.be.bookInfo;


import com.github.pagehelper.PageInfo;
import com.nf.library.controller.be.BaseController;
import com.nf.library.controller.vo.BookInfoPageVo;
import com.nf.library.controller.vo.BookInfoVo;
import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.BookInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BookInfoController extends BaseController {

    @Autowired
    private BookInfoService bookInfoService;

    @RequestMapping("/getAll")
    public PageInfo<BookInfo> getAll(@RequestBody BookInfoPageVo bookInfo){
        List<BookInfo> bookInfos = bookInfoService.getSearchAll(bookInfo.getBookInfo(),bookInfo.getPageVo().getPageNum(),bookInfo.getPageVo().getPageSize());
        PageInfo<BookInfo> pageInfos = new PageInfo<>(bookInfos,bookInfo.getPageVo().getPageSize());
        return pageInfos;
    }

    @RequestMapping("/getIsbn")
    public ResponseVo getIsbn(String isbn){
        BookInfo bookInfo = bookInfoService.getByIsbn(isbn);
        this.checkNull(bookInfo);
        return ResponseVo.builder().code("200").msg("通过验证").data(bookInfo).build();
    }
    @PostMapping("/bookInfoInsert")
    public ResponseVo bookInfoInsert(@RequestBody  @Valid BookInfoVo bookInfoVo, BindingResult bindingResult){
        this.checkNull(bookInfoVo);
        this.validException(bindingResult);
        ResponseVo responseVo = null;
        BookInfo bookInfo = new BookInfo();
        BeanUtils.copyProperties(bookInfoVo,bookInfo);
        bookInfoService.bookInfoInsert(bookInfo);
        responseVo = ResponseVo.builder().code("200").msg("添加成功").build();

        return responseVo;
    }


    @PostMapping("/bookInfoUpdate")
    public ResponseVo bookInfoUpdate(@RequestBody @Valid BookInfoVo bookInfoVo,BindingResult bindingResult){
        this.checkNull(bookInfoVo);
        this.validException(bindingResult);
        ResponseVo responseVo = null;
        BookInfo bookInfo = new BookInfo();
        BeanUtils.copyProperties(bookInfoVo,bookInfo);

        bookInfoService.bookInfoUpdate(bookInfo);
        responseVo = ResponseVo.builder().code("200").msg("修改成功").build();
        return responseVo;
    }

    @GetMapping("bookInfoDeleteById")
    public ResponseVo bookInfoInsert(String isbn){
        this.checkNull(isbn);
        BookInfo info = bookInfoService.getByIsbn(isbn);
        ResponseVo responseVo = null;
        //当库存册数和现存册数相同时才删除
        if(info.getBookStock().equals(info.getTmamount())) {
            bookInfoService.bookInfoByIdDelete(isbn);
            responseVo = ResponseVo.builder().code("200").msg("删除成功").build();
        }else{
            responseVo = ResponseVo.builder().code("400").msg("删除失败，读者正在借阅该数据").build();
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
