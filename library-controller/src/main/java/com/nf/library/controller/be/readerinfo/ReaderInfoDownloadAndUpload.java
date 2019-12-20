package com.nf.library.controller.be.readerinfo;

import com.nf.library.controller.be.BaseVaildExcelController;
import com.nf.library.controller.vo.ReaderInfoVo;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.ReaderInfoService;
import com.nf.library.utils.ReadExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sam
 */
@RestController
@RequestMapping("/admin/reader")
public class ReaderInfoDownloadAndUpload extends BaseVaildExcelController {
    @Autowired
    private ReaderInfoService readerInfoService;

    @PostMapping("/upload")
    public ResponseVo upload(MultipartFile[] excel, HttpServletRequest request)
            throws Exception {
        //第一步检查表头是否符合读者信息表的要求
        //第二部把所有的数据进行校验，如果发现错误则返回改行错误
        //第三步读取正确校验过的数据
        //生成集合
        //批量添加到数据库
        //校验成功的数据
        List<ReaderInfoVo> readerInfoVos = new ArrayList<>();
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
                        this.setEntityValue(readerInfoVo, getKey, value);
                    }
                    readerInfoVos.add(readerInfoVo);
                }
            }
        }
        //表格数据校验 返回错误数据
        List<ReaderInfoVo> readerInfoErrors = this.setVaild(readerInfoVos);
        // 保存正确数据
        List<ReaderInfo> readerInfos = new ArrayList<>();
        //复制数据
        for (ReaderInfoVo readerInfoVo : readerInfoVos) {
            ReaderInfo readerInfo = new ReaderInfo();
            BeanUtils.copyProperties(readerInfoVo,readerInfo);
            readerInfos.add(readerInfo);
        }

        this.checkNull(readerInfos);
        //批量添加数据到数据库中
        readerInfoService.readerInfoBatchInsert(readerInfos);
        return ResponseVo.builder().code("200").data(readerInfoErrors).build();
    }


}
