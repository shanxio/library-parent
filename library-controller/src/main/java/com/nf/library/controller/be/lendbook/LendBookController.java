package com.nf.library.controller.be.lendbook;

import com.github.pagehelper.PageInfo;
import com.nf.library.controller.be.BaseController;
import com.nf.library.controller.vo.LendBookPageVo;
import com.nf.library.controller.vo.LendBookVo;
import com.nf.library.entity.BookInfo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.BookInfoService;
import com.nf.library.service.LendBookService;
import com.nf.library.service.ReaderInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sam
 */
@RestController
@RequestMapping("/admin/lendbook")
public class LendBookController extends BaseController {
    @Autowired
    private ReaderInfoService readerInfoService;
    @Autowired
    private LendBookService lendBookService;
    @Autowired
    private BookInfoService bookInfoService;

    @PostMapping("/getAll")
    public PageInfo<LendBook> getAll(@RequestBody LendBookPageVo lend){
        List<LendBook> lendBooks = lendBookService.getAll(lend.getLend(),
                lend.getPageVo().getPageNum(),lend.getPageVo().getPageSize());
        PageInfo<LendBook> pageInfos = new PageInfo<>(lendBooks,lend.getPageVo().getPageSize());
        return pageInfos;
    }

    @PostMapping("/getNoReturn")
    public PageInfo<LendBook> getNoReturn(@RequestBody LendBookPageVo lend){
        LendBook lendBook = lend.getLend();
        lendBook.setLendState(1);
        List<LendBook> lendBooks = lendBookService.getAll(lendBook,
                lend.getPageVo().getPageNum(),lend.getPageVo().getPageSize());
        PageInfo<LendBook> pageInfos = new PageInfo<>(lendBooks,lend.getPageVo().getPageSize());
        return pageInfos;
    }
    @PostMapping("/lendBookInsert")
    public ResponseVo lendBookInsert(@RequestBody  LendBookVo lendBookVo, BindingResult bindingResult){
        this.checkNull(lendBookVo);
        this.validException(bindingResult);
        LendBook lendBook = new LendBook() ;
        BeanUtils.copyProperties(lendBookVo,lendBook);
        lendBookService.lendBookInsert(lendBook);
        return ResponseVo.builder().code("200").msg("添加成功").build();
    }


    @PostMapping("/lendBookBatchDelete")
    public ResponseVo lendBookBatchDelete(@RequestBody  Integer[] id){
        this.checkNull(id);
        lendBookService.lendBookBatchDelete(id);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }
    @PostMapping("/lendBookUpdate")
    public ResponseVo lendBookUpdate(LendBook lendBook){
        this.checkNull(lendBook);
        lendBookService.lendBookUpdate(lendBook);
        return ResponseVo.builder().code("200").msg("修改成功").build();
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/lendBookDelete")
    public ResponseVo lendBookBatchDelete(Integer id){
        this.checkNull(id);
        lendBookService.lendBookDelete(id);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }

    /**
     * 此修改是用作归还使用
     * @param lendBook
     * @return
     */
    @PostMapping("/returnBook")
    public ResponseVo returnBook(@RequestBody LendBook lendBook){
        this.checkNull(lendBook);
        lendBookService.returnBook(lendBook);
        return ResponseVo.builder().code("200").msg("修改成功").build();
    }
    /**
     * 此修改是批量归还使用
     * @param lendBooks
     * @return
     */
    @PostMapping("/returnBatchBook")
    public ResponseVo returnBatchBook(@RequestBody LendBook[] lendBooks){
        this.checkNull(lendBooks);
        for (LendBook lendBook : lendBooks) {
            lendBookService.returnBook(lendBook);
        }
        return ResponseVo.builder().code("200").msg("修改成功").build();
    }
    /**
     * 续借图书
     * @param
     * @return
     */
    @PostMapping("lendTotalcountUpdate")
    public ResponseVo lendTotalcountUpdate(@RequestBody LendBookVo lendBookVo, BindingResult bindingResult){
        this.checkNull(lendBookVo);
        this.validException(bindingResult);
        LendBook lendBook = new LendBook() ;
        BeanUtils.copyProperties(lendBookVo,lendBook);
        return  lendBookService.lendBookTotalUpdate(lendBook);
    }
}
