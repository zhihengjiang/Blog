package com.zhjiang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhjiang.mapper.LinkMapper;
import com.zhjiang.entity.Link;
import com.zhjiang.service.LinkService;

/**
 * @author Thales
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    public List<Link> getLinkData() {
        return linkMapper.getLinkData();
    }

    public List<Link> listLinkData(Map<String, Object> map) {
        return linkMapper.listLinkData(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return linkMapper.getTotal(map);
    }

    public Integer addLink(Link link) {
        return linkMapper.addLink(link);
    }

    public Integer updateLink(Link link) {
        return linkMapper.updateLink(link);
    }

    public Integer deleteLink(Integer id) {
        return linkMapper.deleteLink(id);
    }

}
