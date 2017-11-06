package com.qxf.newsapp.news.net;

import com.qxf.newsapp.data.RxUseCase;
import com.qxf.newsapp.news.net.beans.NewsInfo;
import com.qxf.newsapp.news.net.beans.NewsReauest;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/6.
 */

public class GetNewsInfo extends RxUseCase<GetNewsInfo.Request, GetNewsInfo.Response> {

    private NewsDataSource newsDataSource;

    public GetNewsInfo(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(Request requestValues) {
        return newsDataSource.getNewsList(requestValues);
    }

    public static class Request implements RxUseCase.RequestValues {
        private NewsReauest newsReauest;

        public Request(NewsReauest newsReauest) {
            this.newsReauest = newsReauest;
        }

        public NewsReauest getRequest() {
            return newsReauest;
        }
    }

    public static class Response implements RxUseCase.ResponseValue {
        private NewsInfo newsInfo;

        public Response(NewsInfo newsInfo) {
            this.newsInfo = newsInfo;
        }

        public NewsInfo getNewsInfo() {
            return newsInfo;
        }
    }
}
