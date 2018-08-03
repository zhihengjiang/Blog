package com.zhjiang.controller.foreground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录页面的控制器
 */
@Controller
@RequestMapping("/user")
public class AcountLogInController {

    @RequestMapping("/login")
    public String userLogin(){
        return "/login";
    }
}
