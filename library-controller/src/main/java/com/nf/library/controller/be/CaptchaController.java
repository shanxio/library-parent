package com.nf.library.controller.be;


import com.google.code.kaptcha.Producer;
import com.nf.library.execption.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    private Map<String,String> maps = new HashMap<>();
    @RequestMapping("/captcha.jpg")
//    @CrossOrigin(allowCredentials = "true")
    public void getCaptchar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置内容类型
        response.setContentType("image/jpeg");
        //创建验证码文本
        String capText = captchaProducer.createText();
        //将验证码文本设置到session中
        request.getSession().setAttribute("captcha",capText);
        //创建验证码图片
        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream outputStream = response.getOutputStream();
        //将图片验证码数据写到响应输出流
        ImageIO.write(bi,"jpg",outputStream);
        try {
            outputStream.flush();
        }finally {
            outputStream.close();
        }
    }
}
