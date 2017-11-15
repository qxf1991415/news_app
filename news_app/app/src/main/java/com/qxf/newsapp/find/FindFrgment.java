package com.qxf.newsapp.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.qxf.newsapp.R;
import com.qxf.newsapp.base.BaseSupportFragment;
import com.qxf.newsapp.data.AppInjection;
import com.qxf.newsapp.find.net.bean.HealthDetailInfo.ShowapiResBodyBean.ItemBean;
import com.qxf.newsapp.find.net.bean.HealthListInfo.ShowapiResBodyBean.ListBean;
import com.qxf.newsapp.find.net.bean.HealthSearchInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/15.
 */

public class FindFrgment extends BaseSupportFragment implements FindContract.View {

    @BindView(R.id.search)
    Button search;
    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    private FindPresent findPresent;
    private boolean isLoadMore = false;
    private List<ListBean> healthList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        view.setOnTouchListener(new DontSpillOnTouchListener());
        ButterKnife.bind(this, view);
        findPresent = new FindPresent(this, getActivity(), AppInjection.provideGetHealthListInfo(),
                AppInjection.provideGetHealthSearchInfo(), AppInjection.provideGetHealthDetailInfo());
        initListData();
        initView();
        return view;
    }

    private void initView() {
    }

    private void initTabLayout() {
        for (int i = 0; i < healthList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(healthList.get(i).getName()), i == 0);
        }
    }

    private void initListData() {
        findPresent.getHealthListData();
    }

    @OnClick(R.id.search)
    public void onViewClicked() {
    }

    @Override
    public void setListData(List<ListBean> healthListInfos) {
        this.healthList.addAll(healthListInfos);
        initTabLayout();
    }

    @Override
    public void setSearchData(List<ContentlistBean> healthSearchInfos) {

    }

    @Override
    public void setDetailData(ItemBean healthDetailInfo) {

    }
}
