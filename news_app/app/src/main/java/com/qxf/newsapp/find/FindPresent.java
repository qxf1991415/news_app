package com.qxf.newsapp.find;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.qxf.newsapp.base.AppConstant;
import com.qxf.newsapp.find.net.bean.HealthDetailInfo.ShowapiResBodyBean.ItemBean;
import com.qxf.newsapp.find.net.bean.HealthListInfo;
import com.qxf.newsapp.find.net.bean.HealthListInfo.ShowapiResBodyBean.ListBean;
import com.qxf.newsapp.find.net.bean.HealthReauest;
import com.qxf.newsapp.find.net.bean.HealthSearchInfo;
import com.qxf.newsapp.find.net.bean.HealthSearchInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import com.qxf.newsapp.find.net.list.GetHealthDetailInfo;
import com.qxf.newsapp.find.net.list.GetHealthListInfo;
import com.qxf.newsapp.find.net.list.GetHealthSearchInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/15.
 */

public class FindPresent implements FindContract.Present {

    private static final int AUTO_SEARCH = 1;
    private static final int SELF_SEARCH = 2;

    private boolean isLoadMore = false;
    private FindContract.View findView;
    private Context context;
    private GetHealthListInfo getHealthListInfo;
    private GetHealthSearchInfo getHealthSearchInfo;
    private GetHealthDetailInfo getHealthDetailInfo;
    private HealthReauest healthReauest;
    private int page = 1;
    private int num = 20;
    private CompositeDisposable compositeDisposable;
    private List<ListBean> healthList = new ArrayList<>();
    private List<ContentlistBean> healthSearch = new ArrayList<>();
    private ItemBean healthDetail;
    private int position;
    private List<ListBean> list;

    public FindPresent(FindContract.View findView, Context context, GetHealthListInfo getHealthListInfo,
                       GetHealthSearchInfo getHealthSearchInfo, GetHealthDetailInfo getHealthDetailInfo) {
        this.findView = findView;
        this.context = context;
        this.getHealthListInfo = getHealthListInfo;
        this.getHealthSearchInfo = getHealthSearchInfo;
        this.getHealthDetailInfo = getHealthDetailInfo;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start() {

    }

    @Override
    public GetHealthListInfo.Request initListRequest() {
        healthReauest = new HealthReauest();
        healthReauest.setAppid(AppConstant.APP_ID);
        healthReauest.setSecret(AppConstant.APP_SECRET);
        return new GetHealthListInfo.Request(healthReauest);
    }

    @Override
    public GetHealthSearchInfo.Request initSearchRequest() {
        healthReauest = new HealthReauest();
        healthReauest.setAppid(AppConstant.APP_ID);
        healthReauest.setSecret(AppConstant.APP_SECRET);
        return null;
    }

    @Override
    public GetHealthDetailInfo.Request initDetailRequest() {
        return null;
    }

    @Override
    public void getHealthListData() {
        getHealthListInfo.executeUseCase(initListRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetHealthListInfo.Response>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GetHealthListInfo.Response response) {
                        HealthListInfo healthListInfo = response.getHealthListInfo();
                        list = healthListInfo.getShowapi_res_body().getList();
                        findView.setListData(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHealthSearchData(GetHealthSearchInfo.Request request, final int tag) {
        getHealthSearchInfo.executeUseCase(request)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetHealthSearchInfo.Response>() {

                    private HealthSearchInfo.ShowapiResBodyBean.PagebeanBean pagebean;

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GetHealthSearchInfo.Response response) {
                        if (AUTO_SEARCH == tag) {
                            pagebean = response.getHealthSearchInfo().getShowapi_res_body().getPagebean();
                            List<ContentlistBean> contentlist = pagebean.getContentlist();
                        } else {

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHealthDetailData() {
        getHealthDetailInfo.executeUseCase(initDetailRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetHealthDetailInfo.Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GetHealthDetailInfo.Response response) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setTabListen(TabLayout tabLayout) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ListBean bean = list.get(tab.getPosition());
                healthReauest = new HealthReauest();
                healthReauest.setAppid(AppConstant.APP_ID);
                healthReauest.setSecret(AppConstant.APP_SECRET);
                healthReauest.setTid(String.valueOf(bean.getId()));
                healthReauest.setKey(bean.getName());
                GetHealthSearchInfo.Request request = new GetHealthSearchInfo.Request(healthReauest);
                getHealthSearchData(request, AUTO_SEARCH);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
