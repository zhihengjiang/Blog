package com.zhjiang.controller.foreground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录页面的控制器
 */
@Controller
public class AccountLogInController {
    @RequestMapping("/login")
    public String userLogin(){
        return "/login";
    }
}
