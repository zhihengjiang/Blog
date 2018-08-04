package com.zhjiang.dao;

import java.util.List;
import java.util.Map;

import com.zhjiang.entity.Link;

/**
 * @author Thales
 *
 */
public interface LinkDao {

    List<Link> getLinkData();

    List<Link> listLinkData(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    Integer addLink(Link link);

    Integer updateLink(Link link);

    Integer deleteLink(Integer id);
}
