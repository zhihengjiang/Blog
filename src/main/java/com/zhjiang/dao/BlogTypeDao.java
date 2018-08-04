package com.zhjiang.dao;

import java.util.List;
import java.util.Map;

import com.zhjiang.entity.BlogType;

/**
 * @author Thales
 *
 */
public interface BlogTypeDao {

    public List<BlogType> getBlogTypeData();

    public BlogType findById(Integer id);

    public List<BlogType> listBlogType(Map<String, Object> map);

    public Long getTotal(Map<String, Object> map);

    public Integer addBlogType(BlogType blogType);

    public Integer updateBlogType(BlogType blogType);

    public Integer deleteBlogType(Integer id);

}
