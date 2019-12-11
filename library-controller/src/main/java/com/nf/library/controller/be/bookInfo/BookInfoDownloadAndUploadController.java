package com.nf.library.controller.be.bookInfo;

import com.nf.library.controller.config.ExportExcelWrapper;
import com.nf.library.controller.vo.BookInfoVo;
import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
import com.nf.library.service.BookInfoService;
import com.nf.library.utils.ExcelConfig;
import com.nf.library.utils.ExportExcelUtil;
import com.nf.library.utils.ReadExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用于图书信息表的上传与下载
 * @author Sam
 */
@RestController
@RequestMapping("/admin/bookInfo")
@MultipartConfig
@Slf4j
public class BookInfoDownloadAndUploadController{

    @Autowired
    private BookInfoService bookInfoService;
    @RequestMapping("/download")
    public void download(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "endPage",defaultValue = "1") Integer endPage,
                                                        @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize,
                                                        HttpServletResponse response) throws IOException {

        List<BookInfo> bookInfos = bookInfoService.getAll(pageNum,pageSize*endPage);
        String[] columnNames = { "id", "isbn", "图书名称","作者","类型",
                "出版社名称","价格","库存册数","现存册数","状态" };
        String fileName = UUID.randomUUID().toString();
        String title = "图书信息表格";
        ExportExcelWrapper<BookInfo> exportExcelWrapper = new ExportExcelWrapper<BookInfo>();

        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        exportExcelWrapper.exportExcel(fileName,title,columnNames,bookInfos,response, ExportExcelUtil.EXCEL_FILE_2003);
    }


    @PostMapping("/upload")
    public void upload(MultipartFile[] excel, HttpServletRequest request) throws IOException, IllegalAccessException, InstantiationException {
        String[] columnNames = { "id", "isbn", "图书名称","作者","类型",
                "出版社名称","价格","库存册数","现存册数","状态" };
        for (MultipartFile myFile : excel) {
            if(myFile.getSize()>0){
                FileInputStream fileInputStream = (FileInputStream) myFile.getInputStream();
                ReadExcelUtil read = new ReadExcelUtil();
                List<Map<String,String>> list = read.readExcel2003(fileInputStream,0,0,0);
                if(list!=null) {
                    BookInfo bookInfo = new BookInfo();
                    for (int i = 0; i < list.size(); i++) {
                        Map<String, String> m = list.get(i);


//                        String val = m.get(columnNames[i]);
//                        Class clz = BookInfoVo.class;
//                        Field[] clzFiles = clz.getDeclaredFields();
//                        if(clzFiles[i].isAnnotationPresent(ExcelConfig.class)) {
//                            ExcelConfig excelConfig = clzFiles[i].getAnnotation(ExcelConfig.class);
//                            if(excelConfig.value()==columnNames[i]){
//                                clzFiles[i].setAccessible(true);
//                                clzFiles[i].set(bookInfo,val);
//                                clzFiles[i].setAccessible(false);
//                            }
//                        }

                        for (Map.Entry<String, String> e : m.entrySet()) {
                           log.info(e.getValue() + "   ");
                        }
                    }
                }

            }
        }
    }
}

