package com.qxf.newsapp.find.net.list;

import com.qxf.newsapp.data.RxUseCase;
import com.qxf.newsapp.find.net.bean.HealthDetailInfo;
import com.qxf.newsapp.find.net.bean.HealthReauest;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GetHealthDetailInfo extends RxUseCase<GetHealthDetailInfo.Request, GetHealthDetailInfo.Response> {

    private HealthDataSource healthDataSource;

    public GetHealthDetailInfo(HealthDataSource healthDataSource) {
        this.healthDataSource = healthDataSource;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(Request requestValues) {
        return healthDataSource.getHealthDeatil(requestValues);
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
        private HealthDetailInfo healthDetailInfo;

        public Response(HealthDetailInfo healthDetailInfo) {
            this.healthDetailInfo = healthDetailInfo;
        }

        public HealthDetailInfo getHealthDetailInfo() {
            return healthDetailInfo;
        }
    }
}
