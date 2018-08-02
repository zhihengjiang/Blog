package com.zhjiang.dao;

import java.util.List;
import java.util.Map;

import com.zhjiang.entity.Comment;

/**
 * @Description ����dao�ӿ�
 * @author Thales
 *
 */
public interface CommentDao {

    // ��ȡ������Ϣ
    public List<Comment> getCommentData(Map<String, Object> map);

    // �������
    public int addComment(Comment comment);

    // ��ȡ�ܼ�¼��
    public Long getTotal(Map<String, Object> map);

    // �޸�������Ϣ
    public Integer update(Comment comment);

    // ɾ��������Ϣ
    public Integer deleteComment(Integer id);

    // ���ݲ���idɾ��������Ϣ������ɾ��ĳƪ����ǰ����ɾ���ò��͵����ۣ���Ϊ�����
    public Integer deleteCommentByBlogId(Integer blogId);
}
