package com.nf.library.controller.be.readerinfo;

import com.github.pagehelper.PageInfo;
import com.nf.library.controller.be.BaseController;
import com.nf.library.controller.vo.ReaderInfoPageVo;
import com.nf.library.controller.vo.ReaderInfoVo;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.ReaderInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sam
 */
@RestController
@RequestMapping("/admin/reader")
public class ReaderInfoController extends BaseController {

    @Autowired
    private ReaderInfoService readerInfoService;

    @PostMapping("/getById")
    public ResponseVo getById(Integer id){
        ReaderInfo readerInfo =  readerInfoService.getById(id);
        this.checkNull(readerInfo);
        return ResponseVo.builder().code("200").msg("通过验证").data(readerInfo).build();
    }


    @PostMapping("/getAll")
    public PageInfo<ReaderInfo> getAll(@RequestBody ReaderInfoPageVo reader){
        this.checkNull(reader);
        List<ReaderInfo> readerInfos = readerInfoService.
                getAll(reader.getReader(),reader.getPageVo().getPageNum(),reader.getPageVo().getPageSize());
        PageInfo<ReaderInfo> pageInfo = new PageInfo<>(readerInfos,reader.getPageVo().getPageSize());
        return pageInfo;
    }

    @PostMapping("/readerInfoInsert")
    public ResponseVo readerInfoInsert(@RequestBody @Valid ReaderInfoVo readerInfoVo, BindingResult bindingResult){
        this.checkNull(readerInfoVo);
        this.validException(bindingResult);
        ReaderInfo readerInfo = new ReaderInfo();
        BeanUtils.copyProperties(readerInfoVo,readerInfo);
        readerInfoService.readerInfoInsert(readerInfo);

        return ResponseVo.builder().code("200").msg("添加成功").build();

    }


    @PostMapping("/readerInfoDelete")
    public ResponseVo readerInfoDelete(Integer id){
        this.checkNull(id);
        readerInfoService.readerInfoDelete(id);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }
    @PostMapping("/readerInfoBatchDelete")
    public ResponseVo readerInfoBatchDelete(@RequestBody Integer[] id){
        this.checkNull(id);
        readerInfoService.readerInfoBatchDelete(id);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }

    /**
     * 修改读者状态
     * @param readerInfo
     * @return
     */
    @PostMapping("/readerInfoStateUpdate")
    public ResponseVo readerInfoUpdate(@RequestBody ReaderInfo readerInfo){
        this.checkNull(readerInfo);
        readerInfoService.readerInfoUpdate(readerInfo);
        return ResponseVo.builder().code("200").msg("修改成功").build();
    }
}
