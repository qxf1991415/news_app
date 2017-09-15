package com.qxf.newsapp.main;

import com.qxf.newsapp.BasePresenter;
import com.qxf.newsapp.BaseView;

/**
 * Created by Administrator on 2017/9/15.
 */

public interface MainContract {

    interface MainPresent extends BasePresenter {
    }

    interface MainView extends BaseView<MainPresent> {
    }
}
