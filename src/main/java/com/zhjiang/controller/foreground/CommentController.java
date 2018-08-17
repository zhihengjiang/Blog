package com.zhjiang.controller.foreground;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zhjiang.entity.Blog;
import com.zhjiang.entity.Comment;
import com.zhjiang.service.BlogService;
import com.zhjiang.service.CommentService;
import com.zhjiang.util.IPUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客页面评论
 * @author Thales
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;

    /**
     * 评论提交
     * @param comment 评论内容
     * @param imageCode 验证码
     * @param request 请求数据
     * @param session 验证码数据
     * @return 提交结果json
     */
    @RequestMapping(value = "/save",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String save(
            Comment comment,
            @RequestParam("imageCode")String imageCode,
            HttpServletRequest request,
            HttpSession session) {

        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0;
        if(!imageCode.equals(sRand)) {
            result.put("success", false);
            result.put("errorInfo", "验证码错误！");
        } else {
            String userIp = IPUtil.getIpAddress(request);
            comment.setUserIp(userIp);
            if(comment.getId() == null) {
                resultTotal = commentService.addComment(comment);
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit() + 1);
                blogService.update(blog);
            } else {

            }
        }
        if(resultTotal > 0) {
            result.put("success", true);
        }
        return result.toString();
    }
}
