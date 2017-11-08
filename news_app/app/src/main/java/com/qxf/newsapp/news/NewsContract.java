package com.qxf.newsapp.news;

import com.qxf.newsapp.news.net.GetNewsInfo;
import com.qxf.newsapp.news.net.beans.NewsInfo;

import java.util.List;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface NewsContract {

    interface View extends com.qxf.newsapp.base.BaseView<Present> {
        void setData(List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfos );
    }

    interface Present extends com.qxf.newsapp.base.BasePresenter {

        GetNewsInfo.Request initRequest();

        void getNewsData();

        void loadMore();

        void refershNews();

    }
}
