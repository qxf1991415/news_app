package com.qxf.newsapp.base.di.component;

import android.content.Context;

import com.qxf.newsapp.base.di.module.NewsModule;
import com.qxf.newsapp.news.NewsAdapter;
import com.qxf.newsapp.news.NewsFrgment;
import com.qxf.newsapp.news.NewsPresent;
import com.qxf.newsapp.news.net.GetNewsInfo;

import dagger.Component;

/**
 * Created by Administrator on 2017/12/19.
 */
@Component(modules = NewsModule.class)
public interface NewsComponent {

    NewsAdapter provideNewsAdapter();

    NewsPresent provideNewsPresent();

    Context provideContext();

    GetNewsInfo provideGetNewsInfo();

    void inject(NewsFrgment frgment);
}
