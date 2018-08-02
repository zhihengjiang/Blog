    package com.zhjiang.dao;

import com.zhjiang.entity.Blogger;

    /**
 * @Description ����dao�ӿ�
 * @author Thales
 *
 */
public interface BloggerDao {

    //ͨ���û�����ѯ����
    public Blogger getByUsername(String username);

    //��ȡ������Ϣ
    public Blogger getBloggerData();

    // ���²���������Ϣ
    public Integer updateBlogger(Blogger blogger);
}
