package com.qxf.newsapp.data;

import com.qxf.newsapp.login.LoginDataSource;
import com.qxf.newsapp.login.LoginRepository;
import com.qxf.newsapp.news.net.GetNewsInfo;
import com.qxf.newsapp.news.net.NewsDataRepository;
import com.qxf.newsapp.news.net.NewsDataSource;


/**
 * Created by Administrator on 2017/11/6.
 */

public class AppInjection {

    public static LoginDataSource provideLoginDataSource() {
        return new LoginRepository();
    }

    public static NewsDataSource provideNewsDataSource() {
        return new NewsDataRepository();
    }

    public static GetNewsInfo provideGetNewsInfo() {
        return new GetNewsInfo(provideNewsDataSource());
    }
}
