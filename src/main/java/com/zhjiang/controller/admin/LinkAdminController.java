package com.zhjiang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.zhjiang.entity.Link;
import com.zhjiang.entity.PageBean;
import com.zhjiang.service.LinkService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *关于友情链接管理页面的请求
 * @author Thales
 *
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    /**
     *友情链接列表
     * @param page 页码
     * @param rows 行数
     * @return
     */
    @RequestMapping(value = "/listLink",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String listLink(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows) {

        PageBean pageBean = new PageBean(Integer.parseInt(page),
                Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();

        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Link> linkList = linkService.listLinkData(map);
        Long total = linkService.getTotal(map);

        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        return result.toString();
    }

    /**
     *友情链接管理
     * @return 视图名
     */
    @RequestMapping("/linkManage")
    public String linkManage(){
        return "/admin/linkManage";
    }


    /**
     *保存添加的友情链接
     * @param link 链接数据
     * @return 操作结果的json

     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(Link link) {

        int resultTotal = 0;
        if (link.getId() == null) {
            resultTotal = linkService.addLink(link);
        } else {
            resultTotal = linkService.updateLink(link);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result.toString();
    }

    /**
     *删除选中的友情链接
     * @param ids 要删除的友情链接集合，以“,"分割
     * @return 操作结果的json
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteLink(
            @RequestParam(value = "ids", required = false) String ids)  {

        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            linkService.deleteLink(id);
        }
        result.put("success", true);
        return result.toString();
    }
}
