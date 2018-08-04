    package com.zhjiang.mapper;

import com.zhjiang.entity.Blogger;

    /**
 * @author Thales
 *
 */
public interface BloggerMapper {

    public Blogger getByUsername(String username);

    public Blogger getBloggerData();

    public Integer updateBlogger(Blogger blogger);
}
