package com.qxf.newsapp.find;

import com.qxf.newsapp.BasePresenter;
import com.qxf.newsapp.BaseView;
import com.qxf.newsapp.news.NewsContract;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface FindContract {

    interface View extends BaseView<NewsContract.Present>{

    }
    interface Present extends BasePresenter{

    }
}
