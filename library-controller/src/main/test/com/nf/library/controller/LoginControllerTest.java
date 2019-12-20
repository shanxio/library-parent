package com.nf.library.controller;

import com.nf.library.controller.config.MvcConfig;
import com.nf.library.controller.vo.ReaderInfoVo;
import com.nf.library.execption.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("webapp")
@ContextConfiguration(classes = MvcConfig.class)
@Slf4j
public class LoginControllerTest {


    @Autowired
    public WebApplicationContext wac;

    public MockMvc mockMvc;

    public MockHttpSession session;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 登录测试
     */
    @Test
    public void loginTest() throws Exception {
        MvcResult mvcResult = mockMvc.
                perform(MockMvcRequestBuilders.post("http://localhost:8080/login"))
//                .param("username","admin").param("password","123456"))
//                .andExpect(MockMvcResultMatchers.status().is(200)) //判断返回状态码
                .andDo(MockMvcResultHandlers.print()) //打印出请求和响应的内容
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println("请求状态码：" + status);
    }

    @Test
    public void test(){

        ReaderInfoVo readerInfoVo = new ReaderInfoVo();
        readerInfoVo.setReaderAddress("123");
        readerInfoVo.setReaderFullAddress("123");
        readerInfoVo.setReaderCard("1847670800");
        readerInfoVo.setReaderMoney(new BigDecimal(123));
        readerInfoVo.setReaderName("王武");
        readerInfoVo.setReaderPhone("18476708005");
        ReaderInfoVo readerInfoVo1 = new ReaderInfoVo();
        readerInfoVo1.setReaderAddress("1233");
        readerInfoVo1.setReaderFullAddress("123");
        readerInfoVo1.setReaderCard("1232131231");
        readerInfoVo1.setReaderMoney(new BigDecimal(123));
//        readerInfoVo1.setReaderName("王武");
        readerInfoVo1.setReaderPhone("135576976887");
        List<Object> readerInfoVos = new ArrayList<>();
        readerInfoVos.add(readerInfoVo);
        readerInfoVos.add(readerInfoVo1);
        List<ResponseVo> responseVos = new ArrayList<>();

//
//        List<Object> reasd = setVaild(readerInfoVos);
//        System.out.println("正确数据"+readerInfoVos);
//        System.out.println("错误数据"+reasd);

    }

}