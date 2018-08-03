package com.zhjiang.entity;


public class PageBean {

    private int page; //页码
    private int pageSize; //每页大小
    private int start; //开始index


    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        start = (page-1)*pageSize;
        return start;
    }

}
