package com.qxf.newsapp.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxf.newsapp.R;
import com.qxf.newsapp.base.BaseSupportFragment;
import com.qxf.newsapp.data.AppInjection;
import com.qxf.newsapp.news.net.beans.NewsInfo;
import com.qxf.newsapp.utils.GlideImageLoader;
import com.ufo.dwrefresh.view.DWRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/15.
 */

public class NewsFrgment extends BaseSupportFragment implements NewsContract.View {
    private List<String> images;
    private NewsPresent newsPresent;
    private List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfoList = new ArrayList<>();

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.dwRefreshLayout)
    DWRefreshLayout dwRefreshLayout;
    @BindView(R.id.news)
    RecyclerView news;
    private NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        view.setOnTouchListener(new DontSpillOnTouchListener());
        ButterKnife.bind(this, view);
        newsPresent = new NewsPresent(this, this.getActivity(), AppInjection.provideGetNewsInfo());
        initData();
        initView();
        return view;
    }

    private void initData() {
        newsPresent.getNewsData();
        dwRefreshLayout.setOnRefreshListener(new DWRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新回调
            }

            @Override
            public void onLoadMore() {
                //加载更多回调
            }
        });
    }

    private void initView() {
        initBanner();
        news.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsAdapter = new NewsAdapter(getActivity());
        newsAdapter.setList(newsInfoList);
        news.setAdapter(newsAdapter);
        news.setItemAnimator(new DefaultItemAnimator());
        news.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        newsAdapter.notifyDataSetChanged();
    }

    private void initBanner() {
        images = new ArrayList<>();
        images.add("http://mob.qipintong.com/assets/js/kindeditor/attached/image/20161114/20161114150030_0003.png");
        images.add("http://mob.qipintong.com/assets/js/kindeditor/attached/image/20161114/20161114145642_5831.png");
        images.add("http://mob.qipintong.com/assets/js/kindeditor/attached/image/20161114/20161114145705_0472.png");
        //设置图片集合
        banner.setImages(images);
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void setData(List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfos) {
        newsInfoList.clear();
        newsInfoList.addAll(newsInfos);
    }
}
