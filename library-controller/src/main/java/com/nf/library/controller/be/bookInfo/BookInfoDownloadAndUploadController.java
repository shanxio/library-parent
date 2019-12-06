package com.nf.library.controller.be.bookInfo;

import com.nf.library.controller.config.ExportExcelWrapper;
import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
import com.nf.library.service.BookInfoService;
import com.nf.library.utils.ExportExcelUtil;
import com.nf.library.utils.ReadExcelUtil;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        exportExcelWrapper.exportExcel(fileName,title,columnNames,bookInfos,response, ExportExcelUtil.EXCEL_FILE_2003);
    }


    @PostMapping("/upload")
    public void upload(MultipartFile[] excel, HttpServletRequest request) throws IOException {
        for (MultipartFile myFile : excel) {
            if(myFile.getSize()>0){
                //获取保存上传文件的file路径
                String path = request.getServletContext().getRealPath("file");
                //获取上传的文件名
                String name = myFile.getOriginalFilename();
                File file = new File(path,name);
                FileInputStream fileInputStream = (FileInputStream) myFile.getInputStream();
                ReadExcelUtil read = new ReadExcelUtil();
                List<Map<String,String>> list = read.readExcel2003(fileInputStream,-1,0,0);
                if(list!=null) {
                    for (int i = 0; i < list.size(); i++) {
                        Map<String, String> m = list.get(i);
                        for (Map.Entry<String, String> e : m.entrySet()) {
                            System.out.print(e.getValue() + "   ");
                        }
                    }
                }

                try {
                    myFile.transferTo(file);
                }catch (IOException e){
                    throw new AppException("上传失败",e);
                }

            }
        }
    }
}

