package com.qxf.newsapp.news;

import android.content.Context;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public class NewsPresent implements NewsContract.Present {

    private NewsContract.View newsView;
    private Context context;

    @Override
    public void start() {

    }

    public NewsPresent(NewsContract.View newsView, Context context) {
        this.newsView = newsView;
        this.context = context;
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refershNews() {

    }
}
