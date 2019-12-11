package com.nf.library.controller.vo;

import com.nf.library.entity.BookInfo;
import lombok.Data;

import java.util.Date;


@Data
public class BookInfoPageVo  {
   private BookInfo bookInfo;
   private PageVo pageVo;

}
