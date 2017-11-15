package com.qxf.newsapp.find.net.list;

import com.qxf.newsapp.data.RxUseCase;
import com.qxf.newsapp.find.net.bean.HealthListInfo;
import com.qxf.newsapp.find.net.bean.HealthReauest;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GetHealthListInfo extends RxUseCase<GetHealthListInfo.Request, GetHealthListInfo.Response> {

    private HealthDataSource healthDataSource;

    public GetHealthListInfo(HealthDataSource healthDataSource) {
        this.healthDataSource = healthDataSource;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(Request requestValues) {
        return healthDataSource.getHealthList(requestValues);
    }

    public static class Request implements RxUseCase.RequestValues{
        private HealthReauest healthReauest;

        public Request(HealthReauest healthReauest) {
            this.healthReauest = healthReauest;
        }

        public HealthReauest getHealthReauest() {
            return healthReauest;
        }
    }

    public static class Response implements RxUseCase.ResponseValue{
        private HealthListInfo healthListInfo;

        public Response(HealthListInfo healthListInfo) {
            this.healthListInfo = healthListInfo;
        }

        public HealthListInfo getHealthListInfo() {
            return healthListInfo;
        }
    }
}
