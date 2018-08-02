package com.zhjiang.controller.admin;

import com.zhjiang.service.BlogService;
import com.zhjiang.service.BlogTypeService;
import com.zhjiang.service.BloggerService;
import com.zhjiang.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Description ����Աϵͳcontroller��
 * @author Thales
 *
 */
@Controller
@RequestMapping("/")
public class TestSystemAdminController {

    @Resource
    private BloggerService bloggerService;
    @Resource
    private LinkService linkService;
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private BlogService blogService;

    // ˢ��ϵͳ����
    @RequestMapping("/refresh")
    public String refreshSystemCache() throws Exception {

        return "/test/main";
    }
}
