package com.zhjiang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhjiang.mapper.BlogMapper;
import com.zhjiang.entity.Blog;
import com.zhjiang.service.BlogService;

/**
 * @author Thales
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    public List<Map<String,Object>> getBlogData() {

        return blogMapper.getBlogData();
    }

    public List<Blog> listBlog(Map<String, Object> map) {
        return blogMapper.listBlog(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return blogMapper.getTotal(map);
    }

    public Blog findById(Integer id) {
        return blogMapper.findById(id);
    }

    public Integer update(Blog blog) {
        return blogMapper.update(blog);
    }

    public Blog getPrevBlog(Integer id) {
        return blogMapper.getPrevBlog(id);
    }

    public Blog getNextBlog(Integer id) {
        return blogMapper.getNextBlog(id);
    }

    public Integer addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    public Integer deleteBlog(Integer id) {
        return blogMapper.deleteBlog(id);
    }

    public Integer getBlogByTypeId(Integer typeId) {
        return blogMapper.getBlogByTypeId(typeId);
    }

}
