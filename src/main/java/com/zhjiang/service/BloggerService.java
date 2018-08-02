package com.zhjiang.service;

import com.zhjiang.entity.Blogger;

/**
 * @Description ����Service�ӿ�
 * @author Thales
 *
 */
public interface BloggerService {

    public Blogger getByUsername(String username);

    public Blogger getBloggerData();

    // ���²���������Ϣ
    public Integer updateBlogger(Blogger blogger);
}
