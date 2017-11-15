package com.qxf.newsapp.data;

import com.qxf.newsapp.base.MyApplication;
import com.qxf.newsapp.data.userdata.UserDataRepository;
import com.qxf.newsapp.data.userdata.UserDataSource;
import com.qxf.newsapp.find.net.list.GetHealthDetailInfo;
import com.qxf.newsapp.find.net.list.GetHealthListInfo;
import com.qxf.newsapp.find.net.list.GetHealthSearchInfo;
import com.qxf.newsapp.find.net.list.HealthDataRepository;
import com.qxf.newsapp.find.net.list.HealthDataSource;
import com.qxf.newsapp.news.net.GetNewsInfo;
import com.qxf.newsapp.news.net.NewsDataRepository;
import com.qxf.newsapp.news.net.NewsDataSource;


/**
 * Created by Administrator on 2017/11/6.
 */

public class AppInjection {

    /**
     * 用户数据
     * @return
     */
    public static UserDataSource provideUserDataSource() {
        return UserDataRepository.getInstance(MyApplication.getContext());
    }

    /**
     * 新闻数据
     * @return
     */
    public static NewsDataSource provideNewsDataSource() {
        return new NewsDataRepository();
    }

    public static GetNewsInfo provideGetNewsInfo() {
        return new GetNewsInfo(provideNewsDataSource());
    }

    /**
     * 发现数据
     * @return
     */
    public static HealthDataSource provideHealthDataSource() {
        return new HealthDataRepository();
    }

    public static GetHealthListInfo provideGetHealthListInfo() {
        return new GetHealthListInfo(provideHealthDataSource());
    }

    public static GetHealthSearchInfo provideGetHealthSearchInfo() {
        return new GetHealthSearchInfo(provideHealthDataSource());
    }

    public static GetHealthDetailInfo provideGetHealthDetailInfo() {
        return new GetHealthDetailInfo(provideHealthDataSource());
    }
}
