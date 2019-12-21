package com.nf.library.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ReturnBook {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private String  bookAuthor;
    private String isbn;
    private Integer readerId;
    private String readerName;
    private Date lendDate;
    private Date returnDate;
    private BigDecimal amount;
    private Integer lendTotalcount;


    /**
     *  用作查询使用
     *  开始日期
     */
    private Date startDate;
    /**
     * 用作查询使用
     * 结束日期
     */
    private Date endDate;

    public ReturnBook() {
    }

    public ReturnBook(Integer id, Integer bookId, String bookName, String bookAuthor, String isbn, Integer readerId, String readerName, Date lendDate, Date returnDate, BigDecimal amount, Integer lendTotalcount, Date startDate, Date endDate) {
        this.id = id;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
        this.readerId = readerId;
        this.readerName = readerName;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
        this.amount = amount;
        this.lendTotalcount = lendTotalcount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
