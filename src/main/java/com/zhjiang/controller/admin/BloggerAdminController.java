package com.zhjiang.controller.admin;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhjiang.entity.Blogger;
import com.zhjiang.service.BloggerService;
import com.zhjiang.util.CryptographyUtil;
import com.zhjiang.util.DateUtil;
import com.zhjiang.util.ResponseUtil;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *  处理后台所有关于博主信息的请求
 * @author Thales
 *
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    @Resource
    private BloggerService bloggerService;

    /**
     * 处理对修改个人信息页面的请求
     * @param model 博主信息
     * @return 个人信息修改页面的试图名
     */
    @RequestMapping("/modifyInfo")
    public String modifyInfo(Model model){
        Blogger blogger = bloggerService.getBloggerData();
        model.addAttribute("blogger",blogger);
        return "/admin/modifyInfo";
    }

    /**
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/findBlogger")
    public String findBlogger(HttpServletResponse response) throws Exception {

        Blogger blogger = bloggerService.getBloggerData();
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     *修改后保存信息
     * @param imageFile 头像文件
     * @param blogger 博主信息
     * @param request 请求
     * @param response
     * @throws Exception
     */

    @RequestMapping("/save")
    public void save(
            @RequestParam("imageFile") MultipartFile imageFile,
            Blogger blogger,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        if(!imageFile.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/");
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "static/userImages/" + imageName));
            blogger.setImagename(imageName);
        }
        int resultTotal = bloggerService.updateBlogger(blogger);
        JSONObject result = new JSONObject();
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        response.getWriter().write(result.toString());
    }

    /**
     *
     * @param password 新密码
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyPassword")
    public String modifyPassword(
            @RequestParam("password") String password,
            HttpServletResponse response) throws Exception {

        Blogger blogger = new Blogger();
        blogger.setPassword(CryptographyUtil.md5(password, "javacoder"));
        int resultTotal = bloggerService.updateBlogger(blogger);
        JSONObject result = new JSONObject();
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }


    /**
     *登出
     * @return 登出后重定向的视图名
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout() throws Exception {

        SecurityUtils.getSubject().logout();
        return "redirect:/user/login.do";
    }

    /**
     *博客系统后台主界面
     * @return 逻辑视图名
     */
    @RequestMapping("/main")
    public String bloggerMain(){
        return "/admin/main";
    }


}
