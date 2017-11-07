package com.qxf.newsapp.news.net;

import com.github.clientcloud.APPCloud;
import com.github.clientcloud.AppServerConfig;
import com.qxf.newsapp.news.net.beans.NewsInfo;
import com.qxf.newsapp.news.net.beans.NewsReauest;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/11/6.
 */

public class NewsDataRepository implements NewsDataSource {
    private NewsApi newsApi;

    public NewsDataRepository() {
        this.newsApi = APPCloud.getInstance()
                .createRetrofitApi(AppServerConfig.class, NewsApi.BASE_URL,NewsApi.class);
    }

    @Override
    public Observable<GetNewsInfo.Response> getNewsList(GetNewsInfo.Request requestValues) {
        NewsReauest request = requestValues.getRequest();
        return newsApi.getNewsInfoByPageCount(request.getAppid(), request.getPage(), request.getNum(), request.getSecret())
                .map(new Function<NewsInfo, GetNewsInfo.Response>() {
                         @Override
                         public GetNewsInfo.Response apply(NewsInfo response) throws Exception {
                             return new GetNewsInfo.Response(response);
                         }
                     }
                );
    }
}
