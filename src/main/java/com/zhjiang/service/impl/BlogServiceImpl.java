package com.zhjiang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhjiang.dao.BlogDao;
import com.zhjiang.entity.Blog;
import com.zhjiang.service.BlogService;

/**
 * @author Thales
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    public List<Map<String,Object>> getBlogData() {

        return blogDao.getBlogData();
    }

    public List<Blog> listBlog(Map<String, Object> map) {
        return blogDao.listBlog(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return blogDao.getTotal(map);
    }

    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    public Integer update(Blog blog) {
        return blogDao.update(blog);
    }

    public Blog getPrevBlog(Integer id) {
        return blogDao.getPrevBlog(id);
    }

    public Blog getNextBlog(Integer id) {
        return blogDao.getNextBlog(id);
    }

    public Integer addBlog(Blog blog) {
        return blogDao.addBlog(blog);
    }

    public Integer deleteBlog(Integer id) {
        return blogDao.deleteBlog(id);
    }

    public Integer getBlogByTypeId(Integer typeId) {
        return blogDao.getBlogByTypeId(typeId);
    }

}
