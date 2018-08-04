    package com.zhjiang.dao;

import com.zhjiang.entity.Blogger;

    /**
 * @author Thales
 *
 */
public interface BloggerDao {

    public Blogger getByUsername(String username);

    public Blogger getBloggerData();

    public Integer updateBlogger(Blogger blogger);
}
