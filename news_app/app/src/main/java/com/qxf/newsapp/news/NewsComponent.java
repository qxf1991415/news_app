package com.qxf.newsapp.news;

import android.content.Context;

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
