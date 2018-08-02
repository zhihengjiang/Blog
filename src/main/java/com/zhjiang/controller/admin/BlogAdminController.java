package com.zhjiang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.zhjiang.entity.Blog;
import com.zhjiang.entity.BlogType;
import com.zhjiang.entity.PageBean;
import com.zhjiang.lucene.BlogIndex;
import com.zhjiang.service.BlogService;
import com.zhjiang.service.BlogTypeService;
import com.zhjiang.service.CommentService;
import com.zhjiang.util.DateJsonValueProcessor;
import com.zhjiang.util.ResponseUtil;
import com.zhjiang.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description ����Ա����Controller��
 * @author Thales
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private CommentService commentService;
    @Resource
    private BlogTypeService blogTypeService;

    private BlogIndex blogIndex = new BlogIndex();

    @RequestMapping("/writeBlog")
    public String writeBlog(Model model){
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        model.addAttribute("blogTypeList",blogTypeList);
        return "/admin/writeBlog";
    }

    @RequestMapping("/blogManage")
    public String blogManage(){

        return "/admin/blogManage";
    }

    @RequestMapping("/save")
    public String save(Blog blog, HttpServletResponse response) throws Exception {

        int resultTotal = 0;
        System.out.println("-------------------------"+blog.getContent());
        if(blog.getId() == null) {
            resultTotal = blogService.addBlog(blog);
            blogIndex.addIndex(blog);
        } else {
            resultTotal = blogService.update(blog);
            blogIndex.updateIndex(blog);
        }

        JSONObject result = new JSONObject();
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }


    @RequestMapping("/listBlog")
    public String listBlog(
            @RequestParam(value="page", required=false)String page,
            @RequestParam(value="rows", required=false)String rows,
            Blog s_blog,
            HttpServletResponse response) throws Exception {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();
        map.put("title", StringUtil.formatLike(s_blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Blog> blogList = blogService.listBlog(map);
        Long total = blogService.getTotal(map);

        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(blogList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/delete")
    public String deleteBlog(
            @RequestParam(value="ids", required=false)String ids,
            HttpServletResponse response) throws Exception {

        String[] idsStr = ids.split(",");
        for(int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            commentService.deleteCommentByBlogId(id);
            blogService.deleteBlog(id);
            blogIndex.deleteIndex(idsStr[i]);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

    //ͨ��id��ȡ����ʵ��
    @RequestMapping("/findById")
    public String findById(
            @RequestParam(value="id", required=false)String id,
            HttpServletResponse response) throws Exception {

        Blog blog = blogService.findById(Integer.parseInt(id));
        JSONObject result = JSONObject.fromObject(blog);
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/modifyBlog")
    public String modifyBlog(@RequestParam("id") int id,Model model){
        Blog blog = blogService.findById(id);
        model.addAttribute("blog",blog);
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        model.addAttribute("blogTypeList",blogTypeList);
        return "/admin/modifyBlog";
    }
}
