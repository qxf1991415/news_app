package com.qxf.newsapp.news;

import android.content.Context;

import com.qxf.newsapp.data.AppInjection;
import com.qxf.newsapp.news.net.GetNewsInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/19.
 */
@Module
public class NewsModule {

    NewsFrgment frgment;

    public NewsModule(NewsFrgment frgment) {
        this.frgment = frgment;
    }

    @Provides
    public NewsPresent provideNewsPresent(GetNewsInfo getNewsInfo) {
        return new NewsPresent(frgment, getNewsInfo);
    }


    @Provides
    public NewsAdapter provideNewsAdapter(Context context) {
        return new NewsAdapter(context);
    }

    @Provides
    public NewsFrgment provideNewsFrgment() {
        return new NewsFrgment();
    }

    @Provides
    public Context provideContext() {
        return frgment.getActivity();
    }

    @Provides
    public GetNewsInfo provideGetNewsInfo() {
        return AppInjection.provideGetNewsInfo();
    }
}
