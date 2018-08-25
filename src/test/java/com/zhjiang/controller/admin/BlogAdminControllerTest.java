package com.zhjiang.controller.admin;

import com.zhjiang.BaseJUnitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class BlogAdminControllerTest extends BaseJUnitTest {
    @Autowired
    WebApplicationContext applicationContext;

    @Test
    public void testUploadImage() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
        File iamge = new File("/home/thales/Pictures/Screenshot/Screenshot from 2017-12-30 19-10-37.png");
        MockMultipartFile file = new MockMultipartFile("editormd-image-file","test.txt","text/plain","sdada".getBytes
                ());
        RequestBuilder request = MockMvcRequestBuilders.multipart("/admin/blog/imageUpload.do").file
                (file);

        MvcResult result = mockMvc.perform(request).andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }
}
