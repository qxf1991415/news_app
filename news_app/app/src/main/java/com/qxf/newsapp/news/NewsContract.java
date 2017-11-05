package com.qxf.newsapp.news;

import com.qxf.newsapp.BasePresenter;
import com.qxf.newsapp.BaseView;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface NewsContract {

    interface View extends BaseView<Present> {

    }

    interface Present extends BasePresenter {

        void loadMore();

        void refershNews();
    }
}
