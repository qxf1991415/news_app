package com.qxf.newsapp.news;

import android.content.Context;

import com.qxf.newsapp.news.net.GetNewsInfo;
import com.qxf.newsapp.news.net.GetNewsInfo.Request;
import com.qxf.newsapp.news.net.beans.NewsInfo;
import com.qxf.newsapp.news.net.beans.NewsReauest;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public class NewsPresent implements NewsContract.Present {

    private boolean isLoadMore = false;
    private NewsContract.View newsView;
    private Context context;
    private GetNewsInfo getNewsInfo;
    private NewsReauest newsReauest = new NewsReauest();
    private int page = 1;
    private int rows = 20;
    private CompositeDisposable compositeDisposable;

    @Override
    public void start() {

    }

    public NewsPresent(NewsContract.View newsView, Context context, GetNewsInfo getNewsInfo) {
        this.newsView = newsView;
        this.context = context;
        this.getNewsInfo = getNewsInfo;
        newsReauest.setKey("684265f995f94eb2b36380a5f66bf807");
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public Request initRequest() {
        if (isLoadMore) {
            ++page;
        } else {
            page = 1;
        }
        newsReauest.setRows(rows);
        newsReauest.setPage(page);
        return new Request(newsReauest);
    }

    @Override
    public void getNewsData() {
        isLoadMore = false;
        getNewsInfo.executeUseCase(initRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetNewsInfo.Response>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GetNewsInfo.Response response) {
                        List<NewsInfo.ResultBean> newsInfos = response.getNewsInfo().getResult();
                        newsView.setData(newsInfos);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    interface LoadFinish{
        void LoadEnd();
    }



    @Override
    public void loadMore() {
        isLoadMore = true;

    }

    @Override
    public void refershNews() {
        isLoadMore = false;

    }
}
