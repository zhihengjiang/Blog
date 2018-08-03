package com.zhjiang.service;

import com.zhjiang.entity.Blogger;

/**
 *
 * @author Thales
 *
 */
public interface BloggerService {

    Blogger getByUsername(String username);

    Blogger getBloggerData();

    Integer updateBlogger(Blogger blogger);
}
