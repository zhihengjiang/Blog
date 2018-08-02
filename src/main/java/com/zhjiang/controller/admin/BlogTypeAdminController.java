package com.zhjiang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.zhjiang.entity.BlogType;
import com.zhjiang.entity.PageBean;
import com.zhjiang.service.BlogService;
import com.zhjiang.service.BlogTypeService;
import com.zhjiang.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @author Thales
 *
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private BlogService blogService;


    @RequestMapping("/blogTypeManage")
    public String blogTypeManage(){
        return "/admin/blogTypeManage";
    }


    @RequestMapping("/listBlogType")
    public String listBlogType(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            HttpServletResponse response) throws Exception {

        PageBean pageBean = new PageBean(Integer.parseInt(page),
                Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<BlogType> blogTypeList = blogTypeService.listBlogType(map);
        Long total = blogTypeService.getTotal(map);

        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }


    @RequestMapping("/save")
    public void save(BlogType blogType, HttpServletResponse response)
            throws Exception {

        int resultTotal = 0;
        String t = blogType.getTypeName();
        System.out.println("id:"+blogType.getId()+",类型名："+t.equals(new String(t.getBytes("utf-8"),"utf-8")));
        System.out.println("id:"+blogType.getId()+",类型名："+t.equals(new String(t.getBytes("GBK"),"GBK")));
        if (blogType.getId() == null) { //TODO 需要修改
            resultTotal = blogTypeService.addBlogType(blogType);
        } else {
            resultTotal = blogTypeService.updateBlogType(blogType);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        response.getWriter().write(result.toString());
//        ResponseUtil.write(response, result.toString());
       // return null;
    }


    @RequestMapping("/delete")
    public void deleteBlog(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response) throws Exception {

        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            if(blogService.getBlogByTypeId(id) > 0) { //该类别有博客
                result.put("exist", "该类别下有博客，不能删除!!");
            } else {
                blogTypeService.deleteBlogType(id);
            }
        }
        result.put("success", true);
        response.getWriter().write(result.toString());
//        ResponseUtil.write(response, result);
//        return null;
    }

    public static void main(String[] args) {
        try {
            String t = "java学习";
            System.out.println(",类型名：" + t.equals(new String(t.getBytes("utf-8"), "utf-8")));
            System.out.println(",类型名：" + new String(t.getBytes("GBK")));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
