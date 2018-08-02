package com.zhjiang.controller.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhjiang.entity.BlogType;
import com.zhjiang.entity.Blogger;
import com.zhjiang.entity.Link;
import com.zhjiang.service.BlogService;
import com.zhjiang.service.BlogTypeService;
import com.zhjiang.service.BloggerService;
import com.zhjiang.service.LinkService;
import com.zhjiang.util.ResponseUtil;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @Description ����Աϵͳcontroller��
 * @author Thales
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

    @Resource
    private BloggerService bloggerService;
    @Resource
    private LinkService linkService;
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private BlogService blogService;

    // ˢ��ϵͳ����
    @RequestMapping("/refreshSystemCache")
    public String refreshSystemCache(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();


        Blogger blogger = bloggerService.getBloggerData();
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);


        List<Link> linkList = linkService.getLinkData();
        application.setAttribute("linkList", linkList);


        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        application.setAttribute("blogTypeList", blogTypeList);


        List<Map<String, Object>> blogTimeList = blogService.getBlogData();
        application.setAttribute("blogTimeList", blogTimeList);

        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
