package com.qxf.newsapp.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxf.newsapp.R;
import com.qxf.newsapp.base.BaseSupportFragment;
import com.qxf.newsapp.data.AppInjection;
import com.qxf.newsapp.main.MainActivity;
import com.qxf.newsapp.detail.Html5Activity;
import com.qxf.newsapp.news.net.beans.NewsInfo;
import com.ufo.dwrefresh.view.DWRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/15.
 */

public class NewsFrgment extends BaseSupportFragment implements NewsContract.View , NewsAdapter.OnItemClickListener{
    private NewsPresent newsPresent;
    private boolean isLoadMore = false;
    private List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfoList = new ArrayList<>();

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
        dwRefreshLayout.setOnRefreshListener(new DWRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                newsPresent.refershNews();
                newsPresent.getNewsData();
                dwRefreshLayout.setRefresh(false);
            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                newsPresent.loadMore();
                newsPresent.getNewsData();
                dwRefreshLayout.setRefresh(false);
            }
        });
    }

    private void initView() {
        isLoadMore = false;
        newsPresent.refershNews();
        newsPresent.getNewsData();
        news.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsAdapter = new NewsAdapter(getActivity());
        newsAdapter.setList(newsInfoList);
        news.setAdapter(newsAdapter);
        news.setItemAnimator(new DefaultItemAnimator());
        newsAdapter.setOnItemClickListener(this);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setData(List<NewsInfo.ShowapiResBodyBean.NewslistBean> newsInfos) {
        if (!isLoadMore) {
            newsInfoList.clear();
        }
        newsInfoList.addAll(newsInfos);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        String url = newsInfoList.get(position - 1).getUrl();
        String title = newsInfoList.get(position - 1).getTitle();
        Intent intent = new Intent((MainActivity)getActivity(), Html5Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("title", title);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
}
