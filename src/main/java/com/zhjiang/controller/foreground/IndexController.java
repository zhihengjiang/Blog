package com.zhjiang.controller.foreground;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zhjiang.entity.Blog;
import com.zhjiang.entity.BlogType;
import com.zhjiang.entity.Blogger;
import com.zhjiang.entity.PageBean;
import com.zhjiang.service.BlogService;
import com.zhjiang.service.BlogTypeService;
import com.zhjiang.service.BloggerService;
import com.zhjiang.util.PageUtil;
import com.zhjiang.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页请求的控制器
 * @author Thales
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private BlogService blogService;
    @Resource
    private BloggerService bloggerService;
    @Resource
    private BlogTypeService blogTypeService;

    /**
     *首页请求
     * @return 视图和模型
     */
    @RequestMapping("/index")
    public ModelAndView index(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
            HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);


        List<Blog> blogList = blogService.listBlog(map);

        for(Blog blog : blogList) {
            List<String> imageList = blog.getImageList();
            String blogInfo = blog.getContent();
            Document doc = Jsoup.parse(blogInfo);
            Elements jpgs = doc.select("img[src$=.jpg]");
            for(int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);
                imageList.add(jpg.toString());
                if(i == 2)
                    break;
            }
        }

        Blogger blogger = bloggerService.getBloggerData();
        modelAndView.addObject("blogger",blogger);

        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        modelAndView.addObject("blogTypeList",blogTypeList);

        List<Map<String, Object>> blogTimeList = blogService.getBlogData();
        modelAndView.addObject("blogTimeList",blogTimeList);


        StringBuffer param = new StringBuffer();
        if(StringUtil.isNotEmpty(typeId)) {
            param.append("typeId=" + typeId + "&");
        }
        if(StringUtil.isNotEmpty(releaseDateStr)) {
            param.append("releaseDateStr=" + releaseDateStr + "&");
        }
        modelAndView.addObject("pageCode", PageUtil.genPagination(
                request.getContextPath() + "/index.html",
                blogService.getTotal(map),
                Integer.parseInt(page), 10,
                param.toString()));

        modelAndView.addObject("blogList", blogList);
        modelAndView.addObject("commonPage", "foreground/blog/blogList.jsp");
        modelAndView.addObject("title", "Thales's Blog");
        modelAndView.setViewName("/mainTemp");

        return modelAndView;

    }
}
