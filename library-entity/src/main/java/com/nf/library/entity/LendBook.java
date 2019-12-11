package com.nf.library.entity;

import lombok.Data;

import java.util.Date;

/**
 * 借阅表
 * @author Sam
 */
@Data
public class LendBook {
    private Integer id;
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private String readerId;
    private String readerName;
    private Date lendDate;
    private Date returnDate;
    private Integer totalcount;
    private Integer lendState;
}
