package com.nf.library.controller.advice;

import com.nf.library.execption.AppException;
import com.nf.library.security.process.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.io.IOException;

/**
 * 错误处理
 * @author Sam
 */
@RestControllerAdvice
public class SystemControllerAdvice {
    @ExceptionHandler(AppException.class)
    public ResponseVo appException(AppException app){
        ResponseVo responseVo = ResponseVo.builder().code("500").msg(app.getMessage()).build();
        return responseVo;
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseVo validationException(ValidationException e){
        ResponseVo responseVo = ResponseVo.builder().code("500").msg("数据校验失败").data(e).build();
        return responseVo;
    }

    @ExceptionHandler(IOException.class)
    public ResponseVo ioException(IOException e){
        ResponseVo responseVo = ResponseVo.builder().code("500").msg("文件上传失败").data(e).build();
        return responseVo;
    }
}
