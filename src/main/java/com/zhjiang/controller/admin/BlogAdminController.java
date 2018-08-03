package com.zhjiang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zhjiang.entity.Blog;
import com.zhjiang.entity.BlogType;
import com.zhjiang.entity.PageBean;
import com.zhjiang.lucene.BlogIndex;
import com.zhjiang.service.BlogService;
import com.zhjiang.service.BlogTypeService;
import com.zhjiang.service.CommentService;
import com.zhjiang.util.DateJsonValueProcessor;
import com.zhjiang.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  后台关于博客管理的控制器
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

    //博客索引，用于博客检索
    private BlogIndex blogIndex = new BlogIndex();

    /**
     * 处理博客撰写的控制器方法
     * @param model 要传递的模型，用于渲染博客撰写的视图
     * @return 要渲染的视图名称
     */
    @RequestMapping("/writeBlog")
    public String writeBlog(Model model){
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        model.addAttribute("blogTypeList",blogTypeList);
        return "/admin/writeBlog";
    }

    /**
     * 处理博客管理页面请求的控制器方法
     * @return 要渲染的视图名称
     */
    @RequestMapping("/blogManage")
    public String blogManage(){

        return "/admin/blogManage";
    }

    /**
     *保存修改或新撰写的博客
     * @param blog 要保存的博客内容
     * @return 要渲染的视图名称
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(Blog blog) throws Exception {

        int resultTotal = 0;
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
        return result.toString();
    }


    /**
     *
     * @param page 页码
     * @param rows 行数
     * @param s_blog
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listBlog", produces = "application/json;charset=utf-8")
    public String listBlog(
            @RequestParam(value="page", required=false)String page,
            @RequestParam(value="rows", required=false)String rows,
            Blog s_blog) {

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
        return result.toString();
    }

    /**
     *处理博客删除的控制器方法
     * @param ids 要删除的博客id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String deleteBlog(
            @RequestParam(value="ids", required=false)String ids) throws Exception {

        String[] idsStr = ids.split(",");
        for(int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            commentService.deleteCommentByBlogId(id);
            blogService.deleteBlog(id);
            blogIndex.deleteIndex(idsStr[i]);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);

        return result.toString();
    }

    /**
     *（暂时没用）
     * @param id 博客id
     * @return 博客数据
     */
    @ResponseBody
    @RequestMapping(value = "/findById",produces = "application/json;charset=utf-8")
    public String findById(
            @RequestParam(value="id", required=false)String id) {

        Blog blog = blogService.findById(Integer.parseInt(id));
        JSONObject result = JSONObject.fromObject(blog);
        return result.toString();
    }

    /**
     *修改博客
     * @param id 博客id
     * @param model 要修改的博客数据
     * @return 修改博客页面的视图
     */

    @RequestMapping("/modifyBlog")
    public String modifyBlog(@RequestParam("id") int id,Model model){
        Blog blog = blogService.findById(id);
        model.addAttribute("blog",blog);
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        model.addAttribute("blogTypeList",blogTypeList);
        return "/admin/modifyBlog";
    }
}
