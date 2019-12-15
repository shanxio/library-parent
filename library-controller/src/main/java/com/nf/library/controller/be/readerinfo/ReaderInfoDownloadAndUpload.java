package com.nf.library.controller.be.readerinfo;

import com.nf.library.controller.vo.ReaderInfoVo;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.utils.ExcelConfig;
import com.nf.library.utils.ReadExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sam
 */
@RestController
@RequestMapping("/admin/reader")
public class ReaderInfoDownloadAndUpload {
    @PostMapping("/upload")
    public ResponseVo upload(MultipartFile[] excel, HttpServletRequest request)
            throws Exception {


        //第一步检查表头是否符合读者信息表的要求
        //第二部把所有的数据进行校验，如果发现错误则返回改行错误
        //第三步读取正确校验过的数据
        //生成集合
        //批量添加到数据库
        //校验成功的数据
        List<ReaderInfo> readerInfos = new ArrayList<>();
        if(excel.length>0){
            for (MultipartFile myFile : excel) {
                FileInputStream fileInputStream = (FileInputStream) myFile.getInputStream();
                ReadExcelUtil read = new ReadExcelUtil();
                //开始读取表格数据
                List<Map<String,String>> lists = read.readExcel2003(fileInputStream,0,0,0);

                for (int i = 0; i < lists.size(); i++) {
                    //每一行的数据
                    Map<String,String> maps =  lists.get(i);
                    Class clz = ReaderInfoVo.class;
                    ReaderInfoVo readerInfoVo = new ReaderInfoVo();
                    for (Map.Entry<String, String> readerInfoEntry : maps.entrySet()) {

                        String getKey = readerInfoEntry.getKey();
                        String value = readerInfoEntry.getValue();
                        setEntityValue(readerInfoVo, getKey, value);
                    }
                    ReaderInfo readerInfo = new ReaderInfo();
                    BeanUtils.copyProperties(readerInfoVo,readerInfo);
                    readerInfos.add(readerInfo);
                }
            }
        }

        System.out.println("list:"+readerInfos.toString());
        return ResponseVo.builder().code("200").build();
    }

    /**
     * 此方法用于将表格中的值赋给对应的实体对象
     * @param obj
     * @param getKey
     * @param value
     * @throws Exception
     */
    private void setEntityValue(Object obj, String getKey, String value) throws Exception {
        ReadExcelUtil read = new ReadExcelUtil();
        // 对应实体类的字段
        Field[] clzFiles =obj.getClass().getDeclaredFields();
        for (int j = 0; j < clzFiles.length ; j++) {
            //判断是否有excelConfig自定义注解
            if(clzFiles[j].isAnnotationPresent(ExcelConfig.class)) {
                //获得对应的注解
                ExcelConfig excelConfig = clzFiles[j].getAnnotation(ExcelConfig.class);
                //对应实体类赋值
                if(excelConfig.value().equals(getKey)){
                    clzFiles[j].setAccessible(true);
                    clzFiles[j].set(obj,read.setParamType(clzFiles[j].getType(),value));
                    clzFiles[j].setAccessible(false);
                    break;
                }
            }
        }
    }
}
