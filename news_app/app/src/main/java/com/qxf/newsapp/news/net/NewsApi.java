package com.qxf.newsapp.news.net;

import com.qxf.newsapp.news.net.beans.NewsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/6.
 */

public interface NewsApi {

    String BASE_URL = "http://api.avatardata.cn/GuoNeiNews/Query/";

    @GET("/")
    Observable<NewsInfo> getNewsInfoByPageCount(@Query("key") String key, @Query("page") int page, @Query("rows") int rows);
}
