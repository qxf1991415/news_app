package com.qxf.newsapp.find.net.list;

import com.qxf.newsapp.data.RxUseCase;
import com.qxf.newsapp.find.net.bean.HealthListInfo;
import com.qxf.newsapp.find.net.bean.HealthReauest;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GetHealthList extends RxUseCase<GetHealthList.Request, GetHealthList.Response> {

    @Override
    protected Observable<Response> buildUseCaseObservable(Request requestValues) {
        return null;
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
