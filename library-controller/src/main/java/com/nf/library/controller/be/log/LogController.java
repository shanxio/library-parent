package com.nf.library.controller.be.log;


import com.github.pagehelper.PageInfo;
import com.nf.library.controller.vo.PageVo;
import com.nf.library.entity.Log;
import com.nf.library.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sam
 */
@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    private LogService logService;
    @PostMapping("/getAll")
    public PageInfo<Log> getAll(@RequestBody PageVo pageVo){
        List<Log> logs = logService.getAll(pageVo.getPageNum(),pageVo.getPageSize());
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
