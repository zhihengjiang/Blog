package com.zhjiang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhjiang.dao.LinkDao;
import com.zhjiang.entity.Link;
import com.zhjiang.service.LinkService;

/**
 * @author Thales
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkDao linkDao;

    public List<Link> getLinkData() {
        return linkDao.getLinkData();
    }

    public List<Link> listLinkData(Map<String, Object> map) {
        return linkDao.listLinkData(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return linkDao.getTotal(map);
    }

    public Integer addLink(Link link) {
        return linkDao.addLink(link);
    }

    public Integer updateLink(Link link) {
        return linkDao.updateLink(link);
    }

    public Integer deleteLink(Integer id) {
        return linkDao.deleteLink(id);
    }

}
