package com.qxf.newsapp.find.net.list;

import com.github.clientcloud.APPCloud;
import com.github.clientcloud.AppServerConfig;
import com.qxf.newsapp.find.net.HealthApi;
import com.qxf.newsapp.find.net.bean.HealthDetailInfo;
import com.qxf.newsapp.find.net.bean.HealthListInfo;
import com.qxf.newsapp.find.net.bean.HealthReauest;
import com.qxf.newsapp.find.net.bean.HealthSearchInfo;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/11/15.
 */

public class HealthDataRepository implements HealthDataSource {

    private HealthApi healthApi;

    public HealthDataRepository() {
        this.healthApi = APPCloud.getInstance().createRetrofitApi(AppServerConfig.class, HealthApi.BASE_URL, HealthApi.class);
    }

    @Override
    public Observable<GetHealthListInfo.Response> getHealthList(GetHealthListInfo.Request requestValues) {
        HealthReauest request = requestValues.getHealthReauest();
        return healthApi.getHealthListInfo(request.getAppid(), request.getSecret())
                .map(new Function<HealthListInfo, GetHealthListInfo.Response>() {
                         @Override
                         public GetHealthListInfo.Response apply(HealthListInfo response) throws Exception {
                             return new GetHealthListInfo.Response(response);
                         }
                     }
                );
    }

    @Override
    public Observable<GetHealthSearchInfo.Response> getHealthSearch(GetHealthSearchInfo.Request requestValues) {
        HealthReauest request = requestValues.getHealthReauest();
        return healthApi.getHealthSearchInfo(request.getAppid(), request.getSecret(),request.getTid(),request.getKey(),request.getPage())
                .map(new Function<HealthSearchInfo, GetHealthSearchInfo.Response>() {
                         @Override
                         public GetHealthSearchInfo.Response apply(HealthSearchInfo response) throws Exception {
                             return new GetHealthSearchInfo.Response(response);
                         }
                     }
                );
    }

    @Override
    public Observable<GetHealthDetailInfo.Response> getHealthDeatil(GetHealthDetailInfo.Request requestValues) {
        HealthReauest request = requestValues.getHealthReauest();
        return healthApi.getHealthDetailInfo(request.getAppid(), request.getSecret(),request.getId())
                .map(new Function<HealthDetailInfo, GetHealthDetailInfo.Response>() {
                         @Override
                         public GetHealthDetailInfo.Response apply(HealthDetailInfo response) throws Exception {
                             return new GetHealthDetailInfo.Response(response);
                         }
                     }
                );
    }
}
