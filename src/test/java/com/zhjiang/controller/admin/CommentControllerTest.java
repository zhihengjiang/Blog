package com.zhjiang.controller.admin;
import com.zhjiang.BaseJUnitTest;
import com.zhjiang.controller.foreground.CommentController;
import com.zhjiang.entity.Comment;
import com.zhjiang.service.BlogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.annotation.Resource;

public class CommentControllerTest extends BaseJUnitTest {

    @Resource
    CommentController commentController;

    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    BlogService blogService;

    @Test
    public void testSaveComment() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
        RequestBuilder request = MockMvcRequestBuilders.post("/comment/save");
        Comment comment = new Comment();
        comment.setContent("1234567");
        comment.setBlog(blogService.getNextBlog(1));

        ((MockHttpServletRequestBuilder) request).param("content","1234567");
        ((MockHttpServletRequestBuilder) request).param("blog.id","14");
        ((MockHttpServletRequestBuilder) request).param("imageCode","1234");
        ((MockHttpServletRequestBuilder) request).sessionAttr("sRand","1234");
        ((MockHttpServletRequestBuilder) request).header("x-forwarded-for","104.128.06.49,114.114.114.114");

        MvcResult result = mockMvc.perform(request).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
