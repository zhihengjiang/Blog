package com.zhjiang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhjiang.dao.BloggerDao;
import com.zhjiang.entity.Blogger;
import com.zhjiang.service.BloggerService;

/**
 * @author Thales
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

    @Resource
    private BloggerDao bloggerDao;

    public Blogger getByUsername(String username) {
        return bloggerDao.getByUsername(username);
    }

    public Blogger getBloggerData() {
        return bloggerDao.getBloggerData();
    }

    public Integer updateBlogger(Blogger blogger) {
        return bloggerDao.updateBlogger(blogger);
    }

}
