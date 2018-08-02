package com.zhjiang.dao;

import java.util.List;
import java.util.Map;

import com.zhjiang.entity.BlogType;

/**
 * @Description �������dao�ӿ�
 * @author Thales
 *
 */
public interface BlogTypeDao {

    // ��ȡ���������Ϣ
    public List<BlogType> getBlogTypeData();

    // ����id���Ҳ���������Ϣ
    public BlogType findById(Integer id);

    // ��ҳ��ѯ���������Ϣ
    public List<BlogType> listBlogType(Map<String, Object> map);

    // ��ȡ�ܼ�¼��
    public Long getTotal(Map<String, Object> map);

    // ��Ӳ������
    public Integer addBlogType(BlogType blogType);

    // ���²������
    public Integer updateBlogType(BlogType blogType);

    // ɾ���������
    public Integer deleteBlogType(Integer id);

}
