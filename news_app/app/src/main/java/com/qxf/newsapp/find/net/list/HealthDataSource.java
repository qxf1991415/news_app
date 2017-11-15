package com.qxf.newsapp.find.net.list;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/9.
 */

public interface HealthDataSource {

    Observable<GetHealthListInfo.Response> getHealthList(GetHealthListInfo.Request requestValues);

    Observable<GetHealthSearchInfo.Response> getHealthSearch(GetHealthSearchInfo.Request requestValues);

    Observable<GetHealthDetailInfo.Response> getHealthDeatil(GetHealthDetailInfo.Request requestValues);
}
