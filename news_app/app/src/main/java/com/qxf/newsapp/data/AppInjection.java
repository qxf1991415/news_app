package com.qxf.newsapp.data;

import com.qxf.newsapp.base.MyApplication;
import com.qxf.newsapp.data.userdata.UserDataRepository;
import com.qxf.newsapp.data.userdata.UserDataSource;
import com.qxf.newsapp.news.net.GetNewsInfo;
import com.qxf.newsapp.news.net.NewsDataRepository;
import com.qxf.newsapp.news.net.NewsDataSource;


/**
 * Created by Administrator on 2017/11/6.
 */

public class AppInjection {

    public static UserDataSource provideUserDataSource() {
        return UserDataRepository.getInstance(MyApplication.getContext());
    }

    public static NewsDataSource provideNewsDataSource() {
        return new NewsDataRepository();
    }

    public static GetNewsInfo provideGetNewsInfo() {
        return new GetNewsInfo(provideNewsDataSource());
    }
}
