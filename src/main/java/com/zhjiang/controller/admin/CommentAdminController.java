package com.zhjiang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zhjiang.entity.Comment;
import com.zhjiang.entity.PageBean;
import com.zhjiang.service.CommentService;
import com.zhjiang.util.DateJsonValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理评论管理页面的请求
 * @author Thales
 *
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    /**
     *评论管理页
     * @return 视图名
     */
    @RequestMapping("/commentManage")
    public String commentManage(){
        return "/admin/commentManage";
    }

    /**
     *评论审阅
     * @return 视图名
     */
    @RequestMapping("/commentReview")
    public String CommentReview(){
        return "/admin/commentReview";
    }

    /**
     *评论列表
     * @param page 页数
     * @param rows 行数
     * @param state 状态
     * @return
     */
    @RequestMapping(value = "/listComment",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String listBlog(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            @RequestParam(value = "state", required = false) String state)  {

        PageBean pageBean = new PageBean(Integer.parseInt(page),
                Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        map.put("state", state);
        List<Comment> commentList = commentService.getCommentData(map);
        Long total = commentService.getTotal(map);

        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class,
                new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(commentList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        return result.toString();

    }


    /**
     *评论审核
     * @param ids 所有要审核的评论id,以“,”符号分割
     * @param state 要设置的状态（通过/不通过）
     * @return 操作成功的json

     */
    @RequestMapping("/review")
    @ResponseBody
    public String review(
            @RequestParam(value = "ids", required = false) String ids,
            @RequestParam(value = "state", required = false) Integer state) {

        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            Comment comment = new Comment();
            comment.setId(Integer.parseInt(idsStr[i]));
            comment.setState(state);
            commentService.update(comment);
        }
        result.put("success", true);
        return result.toString();
    }

    /**
     *评论删除
     * @param ids 所有要删除的评论id
     * @return 操作结果josn
     */
    @RequestMapping("/deleteComment")
    public String deleteBlog(
            @RequestParam(value = "ids", required = false) String ids)  {

        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            commentService.deleteComment(Integer.parseInt(idsStr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result.toString();
    }
}
