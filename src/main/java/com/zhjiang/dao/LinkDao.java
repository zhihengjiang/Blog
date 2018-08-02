package com.zhjiang.dao;

import java.util.List;
import java.util.Map;

import com.zhjiang.entity.Link;

/**
 * @Description ��������dao�ӿ�
 * @author Thales
 *
 */
public interface LinkDao {

    // ��ȡ��������
    public List<Link> getLinkData();

    // ��ҳ��ȡ��������
    public List<Link> listLinkData(Map<String, Object> map);

    // ��ȡ�ܼ�¼��
    public Long getTotal(Map<String, Object> map);

    // �����������
    public Integer addLink(Link link);

    // ������������
    public Integer updateLink(Link link);

    // ɾ����������
    public Integer deleteLink(Integer id);
}
