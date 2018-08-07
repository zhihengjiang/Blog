    package com.zhjiang.mapper;

import com.zhjiang.entity.Blogger;

    /**
 * @author Thales
 *
 */
public interface BloggerMapper {

    Blogger getByUsername(String username);

    Blogger getBloggerData();

    Integer updateBlogger(Blogger blogger);
}
