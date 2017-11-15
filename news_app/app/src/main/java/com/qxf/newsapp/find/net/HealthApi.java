package com.qxf.newsapp.find.net;

import com.qxf.newsapp.find.net.bean.HealthDetailInfo;
import com.qxf.newsapp.find.net.bean.HealthListInfo;
import com.qxf.newsapp.find.net.bean.HealthSearchInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/9.
 */

public interface HealthApi {

    String BASE_URL = "http://route.showapi.com/";

    @GET("90-86")
    Observable<HealthListInfo> getHealthListInfo(@Query("showapi_appid") String appid,
                                                 @Query("showapi_sign") String secret);

    @GET("90-87")
    Observable<HealthSearchInfo> getHealthSearchInfo(@Query("showapi_appid") String appid,
                                                     @Query("showapi_sign") String secret,
                                                     @Query("tid") String tid,
                                                     @Query("key") String key,
                                                     @Query("page") String page);

    @GET("90-88")
    Observable<HealthDetailInfo> getHealthDetailInfo(@Query("showapi_appid") String appid,
                                                     @Query("showapi_sign") String secret,
                                                     @Query("id") String id);
}
