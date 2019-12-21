package com.nf.library.controller.be.returnbook;


import com.github.pagehelper.PageInfo;
import com.nf.library.controller.be.BaseController;
import com.nf.library.controller.vo.LendBookPageVo;
import com.nf.library.controller.vo.ReturnBookPageVo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReturnBook;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sam
 */
@RestController
@RequestMapping("/admin/return")
public class ReturnBookController extends BaseController {

    @Autowired
    private ReturnBookService returnBookService;
    @PostMapping("/getAll")
    public PageInfo<ReturnBook> getAll(@RequestBody ReturnBookPageVo returnBook){
        List<ReturnBook> lendBooks = returnBookService.getAll(returnBook.getReturnBook(),
                returnBook.getPageVo().getPageNum(),returnBook.getPageVo().getPageSize());
        PageInfo<ReturnBook> pageInfos = new PageInfo<>(lendBooks,returnBook.getPageVo().getPageSize());
        return pageInfos;
    }

    @PostMapping("/returnBookBatchDelete")
    public ResponseVo returnBookBatchDelete(@RequestBody  Integer[] ids){
        this.checkNull(ids);
        returnBookService.returnBookBatchDelete(ids);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }
    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/returnBookDelete")
    public ResponseVo returnBookDelete(Integer id){
        this.checkNull(id);
        returnBookService.returnBookDelete(id);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }


}
