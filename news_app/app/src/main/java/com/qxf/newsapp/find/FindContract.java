package com.qxf.newsapp.find;

import android.support.design.widget.TabLayout;

import com.qxf.newsapp.find.net.bean.HealthDetailInfo.ShowapiResBodyBean.ItemBean;
import com.qxf.newsapp.find.net.bean.HealthListInfo.ShowapiResBodyBean.ListBean;
import com.qxf.newsapp.find.net.bean.HealthSearchInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import com.qxf.newsapp.find.net.list.GetHealthDetailInfo;
import com.qxf.newsapp.find.net.list.GetHealthListInfo;
import com.qxf.newsapp.find.net.list.GetHealthSearchInfo;

import java.util.List;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface FindContract {

    interface View extends com.qxf.newsapp.base.BaseView<Present> {

        void setListData(List<ListBean> healthListInfos);

        void setSearchData(List<ContentlistBean> healthSearchInfos);

        void setDetailData(ItemBean healthDetailInfo);
    }

    interface Present extends com.qxf.newsapp.base.BasePresenter {

        GetHealthListInfo.Request initListRequest();

        GetHealthSearchInfo.Request initSearchRequest();

        GetHealthDetailInfo.Request initDetailRequest();

        void getHealthListData();

        void getHealthSearchData(GetHealthSearchInfo.Request request, int tag);

        void getHealthDetailData();

        void setTabListen(TabLayout tabLayout);
    }
}
