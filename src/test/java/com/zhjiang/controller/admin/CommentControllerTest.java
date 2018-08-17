package com.zhjiang.controller.admin;
import com.zhjiang.BaseJUnitTest;
import com.zhjiang.controller.foreground.CommentController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

public class CommentControllerTest extends BaseJUnitTest {

    @Resource
    CommentController commentController;
    @Test
    public void testSaveComment() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        RequestBuilder request = MockMvcRequestBuilders.get("/comment/save?imageCode=1234");
        ((MockHttpServletRequestBuilder) request).sessionAttr("sRand","1231");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers
                .content().json
                ("{\"success\":false}"));
    }
}
