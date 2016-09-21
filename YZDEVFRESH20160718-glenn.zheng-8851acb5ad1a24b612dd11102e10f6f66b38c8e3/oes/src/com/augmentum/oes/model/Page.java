package com.augmentum.oes.model;

import java.io.Serializable;
import java.util.List;

import com.augmentum.oes.Constants;

public class Page implements Serializable{

    private static final long serialVersionUID = 4180135665199133663L;
    private int pagesize ;
    private int pagenum;
    private int totalpage;
    private List records;
    private int startIndex;
    private int totalrecords;
    private int startpage;
    private int endpage;

    private String servleturl;

    public Page(int pagenum, int totalrecords,int pagesize){

        this.pagenum = pagenum;
        this.totalrecords = totalrecords;
        this.pagesize = pagesize;

        startIndex = (pagenum-1)*pagesize;

        if (totalrecords % pagesize== 0) {
            totalpage = totalrecords / pagesize;
        } else {
            totalpage = ((totalrecords / pagesize) + 1 );
        }

        if (totalpage <= Constants.PAGNATION_MAX_SIZE) {
            startpage = Constants.PAGNATION_STATE_PAEE;
            endpage = totalpage;
        } else {
            startpage = pagenum-1;
            endpage = pagenum+1;

            if(startpage < Constants.PAGNATION_STATE_PAEE){
                startpage = Constants.PAGNATION_STATE_PAEE;
                endpage = 3;
            }

            if(endpage>totalpage){
                startpage = totalpage - Constants.PAGNATION_MAX_SIZE;
                endpage = totalpage;
            }
        }
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalrecords() {
        return totalrecords;
    }

    public void setTotalrecords(int totalrecords) {
        this.totalrecords = totalrecords;
    }

    public int getStartpage() {
        return startpage;
    }

    public void setStartpage(int startpage) {
        this.startpage = startpage;
    }

    public int getEndpage() {
        return endpage;
    }

    public void setEndpage(int endpage) {
        this.endpage = endpage;
    }

    public String getServleturl() {
        return servleturl;
    }

    public void setServleturl(String servleturl) {
        this.servleturl = servleturl;
    }
}
