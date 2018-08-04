package com.zhjiang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhjiang.mapper.BloggerMapper;
import com.zhjiang.entity.Blogger;
import com.zhjiang.service.BloggerService;

/**
 * @author Thales
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

    @Resource
    private BloggerMapper bloggerMapper;

    public Blogger getByUsername(String username) {
        return bloggerMapper.getByUsername(username);
    }

    public Blogger getBloggerData() {
        return bloggerMapper.getBloggerData();
    }

    public Integer updateBlogger(Blogger blogger) {
        return bloggerMapper.updateBlogger(blogger);
    }

}
