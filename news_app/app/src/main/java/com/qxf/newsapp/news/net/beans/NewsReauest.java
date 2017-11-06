package com.qxf.newsapp.news.net.beans;

/**
 * Created by Administrator on 2017/11/6.
 */

public class NewsReauest {

    private String key;
    private int page;
    private int rows;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
