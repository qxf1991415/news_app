package com.qxf.newsapp.news;

import com.qxf.newsapp.news.net.GetNewsInfo;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface NewsContract {

    interface View extends com.qxf.newsapp.base.BaseView<Present> {

    }

    interface Present extends com.qxf.newsapp.base.BasePresenter {

        GetNewsInfo.Request initRequest();

        void getNewsData();

        void loadMore();

        void refershNews();
    }
}
