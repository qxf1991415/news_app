package com.qxf.newsapp.news.net;

import com.qxf.newsapp.news.net.beans.NewsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/6.
 */

public interface NewsApi {

    String BASE_URL = "http://route.showapi.com/";

    @GET("196-1")
    Observable<NewsInfo> getNewsInfoByPageCount(@Query("showapi_appid") String appid,
                                                @Query("page") int page, @Query("num") int rows,
                                                @Query("showapi_sign") String secret);
}
