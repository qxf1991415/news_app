package com.qxf.newsapp.news.net;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/6.
 */

public interface NewsDataSource {
    Observable<GetNewsInfo.Response> getNewsList(GetNewsInfo.Request requestValues);
}
