package com.zhjiang.service;

import java.util.List;
import java.util.Map;

import com.zhjiang.entity.Link;

/**
 * @Description ��������Service�ӿ�
 * @author Thales
 *
 */
public interface LinkService {

    // ��ȡ��������
    public List<Link> getLinkData();

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
