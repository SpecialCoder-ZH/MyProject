package com.augmentum.oes.model;



public class PageInfo {

    private String pagesize ;
    private String pagenum;
    private String keywords;
    private String direction ;
    private String nameOrder ;
    private String timeOrder ;
    private String beginDate;
    private String endDate;

    public String getNameOrder() {
        return nameOrder;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getPagenum() {
        return pagenum;
    }

    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
