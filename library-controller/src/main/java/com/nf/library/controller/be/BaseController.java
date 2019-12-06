package com.nf.library.controller.be;

import com.nf.library.execption.AppException;

/**
 * 这是一个所有控制器的父类
 * @author Sam
 */
public class BaseController {
    /**
     *此方法只是用来判断值是否为空
     * @param param
     */
    public void checkNull(Object param){
        if(param==null){
            throw new AppException("数据不能为空");
        }
    }
}
