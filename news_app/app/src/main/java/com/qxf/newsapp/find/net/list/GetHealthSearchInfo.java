package com.qxf.newsapp.find.net.list;

import com.qxf.newsapp.data.RxUseCase;
import com.qxf.newsapp.find.net.bean.HealthReauest;
import com.qxf.newsapp.find.net.bean.HealthSearchInfo;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GetHealthSearchInfo extends RxUseCase<GetHealthSearchInfo.Request, GetHealthSearchInfo.Response> {

    private HealthDataSource healthDataSource;

    public GetHealthSearchInfo(HealthDataSource healthDataSource) {
        this.healthDataSource = healthDataSource;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(Request requestValues) {
        return healthDataSource.getHealthSearch(requestValues);
    }

    public static class Request implements RxUseCase.RequestValues {
        private HealthReauest healthReauest;

        public Request(HealthReauest healthReauest) {
            this.healthReauest = healthReauest;
        }

        public HealthReauest getHealthReauest() {
            return healthReauest;
        }
    }

    public static class Response implements RxUseCase.ResponseValue {
        private HealthSearchInfo healthSearchInfo;

        public Response(HealthSearchInfo healthSearchInfo) {
            this.healthSearchInfo = healthSearchInfo;
        }

        public HealthSearchInfo getHealthSearchInfo() {
            return healthSearchInfo;
        }
    }
}
