package com.zhjiang.controller.foreground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foreground/common")
public class CommonController {
    @RequestMapping("/footer")
    public String footer(){
        return "/foreground/common/footer";
    }

    @RequestMapping("/head")
    public String head(){
        return "/foreground/common/head";
    }

    @RequestMapping("/menu")
    public String menu(){
        return "/foreground/common/menu";
    }

}
