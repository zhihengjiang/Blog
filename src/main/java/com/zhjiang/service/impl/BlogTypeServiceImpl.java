package com.zhjiang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zhjiang.service.BlogTypeService;
import org.springframework.stereotype.Service;

import com.zhjiang.mapper.BlogTypeMapper;
import com.zhjiang.entity.BlogType;

/**
 * @author Thales
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeMapper blogTypeMapper;

    //��ȡ���������Ϣ
    public List<BlogType> getBlogTypeData() {

        return blogTypeMapper.getBlogTypeData();
    }

    public List<BlogType> listBlogType(Map<String, Object> map) {
        return blogTypeMapper.listBlogType(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return blogTypeMapper.getTotal(map);
    }

    public Integer addBlogType(BlogType blogType) {
        return blogTypeMapper.addBlogType(blogType);
    }

    public Integer updateBlogType(BlogType blogType) {
        return blogTypeMapper.updateBlogType(blogType);
    }

    public Integer deleteBlogType(Integer id) {
        return blogTypeMapper.deleteBlogType(id);
    }

}
