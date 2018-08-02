package com.zhjiang.entity;

/**
 * @Description ��������ʵ��
 * @author Thales
 *
 */
public class Link {

    private Integer id;
    private String linkname; //��������
    private String linkurl; //���ӵ�ַ
    private Integer orderNum; //������������

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLinkname() {
        return linkname;
    }
    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }
    public String getLinkurl() {
        return linkurl;
    }
    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }
    public Integer getOrder() {
        return orderNum;
    }
    public void setOrder(Integer orderNum) {
        this.orderNum = orderNum;
    }


}
