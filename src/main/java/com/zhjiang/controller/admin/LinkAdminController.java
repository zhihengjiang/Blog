package com.zhjiang.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.zhjiang.entity.Link;
import com.zhjiang.entity.PageBean;
import com.zhjiang.service.LinkService;
import com.zhjiang.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description ����Ա��������Controller��
 * @author Thales
 *
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;
    // ��ҳ��ѯ��������
    @RequestMapping("/listLink")
    public String listLink(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            HttpServletResponse response) throws Exception {

        PageBean pageBean = new PageBean(Integer.parseInt(page),
                Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Link> linkList = linkService.listLinkData(map);
        Long total = linkService.getTotal(map);

        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/linkManage")
    public String linkManage(){
        return "/admin/linkManage";
    }

    // ��Ӻ͸�����������
    @RequestMapping("/save")
    public String save(Link link, HttpServletResponse response)
            throws Exception {

        int resultTotal = 0; // ���շ��ؽ����¼��
        if (link.getId() == null) { // ˵���ǵ�һ�β���
            resultTotal = linkService.addLink(link);
        } else { // ��id��ʾ�޸�
            resultTotal = linkService.updateLink(link);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    // ����������Ϣɾ��
    @RequestMapping("/delete")
    public String deleteLink(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response) throws Exception {

        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            linkService.deleteLink(id);
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
