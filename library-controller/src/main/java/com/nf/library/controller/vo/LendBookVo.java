package com.nf.library.controller.vo;

import com.nf.library.entity.BookInfo;
import com.nf.library.entity.ReaderInfo;
import lombok.Data;

/**
 * @author Sam
 */
@Data
public class LendBookVo {
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private Integer readerId;
    private String readerName;
}
