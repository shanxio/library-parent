package com.nf.library.service.impl;

import com.nf.library.dao.BookInfoDao;
import com.nf.library.dao.LendBookDao;
import com.nf.library.dao.ReaderInfoDao;
import com.nf.library.dao.ReturnBookDao;
import com.nf.library.entity.BookInfo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.entity.ReturnBook;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.LendBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Sam
 */
@Service
public class LendBookServiceImpl implements LendBookService {
    @Autowired
    private LendBookDao lendBookDao;
    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private ReaderInfoDao readerInfoDao;
    @Autowired
    private ReturnBookDao returnBookDao;
    @Override
    @Transactional(readOnly = false)
    public ResponseVo lendBookInsert(LendBook lendBook) {
        ResponseVo responseVo = null;
        BookInfo bookInfo = bookInfoDao.getByIsbn(lendBook.getIsbn());
        ReaderInfo readerInfo = readerInfoDao.getById(Integer.parseInt(lendBook.getReaderId()));
        if(bookInfo.getBookStock().equals(bookInfo.getTmamount())) {
            responseVo = ResponseVo.builder().code("400").msg("该书籍已经不足").build();
        }else if(readerInfo.getReaderMoney().compareTo(lendBook.getLendMoney())==-1){
            responseVo = ResponseVo.builder().code("400").msg("余额不足").build();
        }else {
            BookInfo bookUpdate = new BookInfo();
            //现存册数减一
            bookUpdate.setBookStock(bookInfo.getBookStock() - 1);
            bookUpdate.setIsbn(lendBook.getIsbn());
            bookInfoDao.bookInfoUpdate(bookUpdate);
            lendBookDao.lendBookInsert(lendBook);

            readerInfo.setReaderMoney(readerInfo.getReaderMoney().subtract(lendBook.getLendMoney()));
            readerInfoDao.readerInfoUpdate(readerInfo);
        }
        return responseVo;
    }

    @Override
    public List<LendBook> getAll(LendBook lendBook, Integer pageNum, Integer pageSize) {
        return lendBookDao.getAll(lendBook,pageNum,pageSize);
    }

    @Override
    public LendBook getById(Integer id) {
        return lendBookDao.getById(id);
    }

    @Override
    public void lendBookBatchDelete(Integer[] id) {
        lendBookDao.lendBookBatchDelete(id);
    }

    @Override
    public void lendBookDelete(Integer id) {
        lendBookDao.lendBookDelete(id);
    }
    @Transactional(readOnly = false)
    @Override
    public void returnBook(LendBook lendBook) {
        ReaderInfo readerInfo = readerInfoDao.getById(Integer.parseInt(lendBook.getReaderId()));
        BookInfo bookInfo = bookInfoDao.getByIsbn(lendBook.getIsbn());
        ReturnBook returnBook = ReturnBook.builder()
                .bookId(bookInfo.getId())
                .bookName(bookInfo.getBookName())
                .bookAuthor(bookInfo.getBookAuthor())
                .isbn(bookInfo.getIsbn())
                .readerId(readerInfo.getId())
                .readerName(readerInfo.getReaderName())
                .lendDate(lendBook.getLendDate())
                .returnDate(new Date())
                .amount(lendBook.getLendMoney())
                .lendTotalcount(lendBook.getLendTotalcount()).build();

        BookInfo bookUpdate = new BookInfo();
        //现存册数减一
        bookUpdate.setBookStock(bookInfo.getBookStock()+1);
        bookUpdate.setIsbn(lendBook.getIsbn());
        bookInfoDao.bookInfoUpdate(bookUpdate);

        returnBookDao.returnBookInsert(returnBook);
        lendBookDao.lendBookDelete(lendBook.getId());

    }
    @Transactional(readOnly = false)
    @Override
    public ResponseVo lendBookTotalUpdate(LendBook lend) {
        ReaderInfo readerInfo = readerInfoDao.getById(Integer.parseInt(lend.getReaderId()));
        ResponseVo responseVo = null;
        if(readerInfo.getReaderMoney().compareTo(lend.getLendMoney())==-1){
            responseVo = ResponseVo.builder().code("400").msg("读者充值金额不足").build();
        }else {
            LendBook lendBook = lendBookDao.getById(lend.getId());
            LendBook book = new LendBook();
            book.setId(lend.getId());
            book.setLendTotalcount(lendBook.getLendTotalcount() + 1);
            BigDecimal bigDecimal = lendBook.getLendMoney().add(lend.getLendMoney());
            book.setLendMoney(bigDecimal);
            book.setLendDay(lend.getLendDay());
            lendBookDao.lendBookUpdate(book);

            readerInfo.setReaderMoney(readerInfo.getReaderMoney().subtract(lend.getLendMoney()));
            readerInfoDao.readerInfoUpdate(readerInfo);
            responseVo = ResponseVo.builder().code("200").msg("修改成功").build();
        }
        return responseVo;
    }

    @Override
    public void lendBookUpdate(LendBook lendBook) {

        lendBookDao.lendBookUpdate(lendBook);
    }


}
