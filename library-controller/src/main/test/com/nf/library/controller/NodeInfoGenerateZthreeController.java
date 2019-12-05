package com.nf.library.controller;


import com.nf.library.controller.config.MvcConfig;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("webapp")
@ContextConfiguration(classes = MvcConfig.class)
@Slf4j
public class NodeInfoGenerateZthreeController {


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
                perform(MockMvcRequestBuilders.post("http://localhost:8080/admin/nodeInfo/ztree"))
//                .param("username","admin").param("password","123456"))
//                .andExpect(MockMvcResultMatchers.status().is(200)) //判断返回状态码
                .andDo(MockMvcResultHandlers.print()) //打印出请求和响应的内容
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println("请求状态码：" + status);
    }

}