package com.qxf.newsapp.main;

import com.qxf.newsapp.base.BasePresenter;
import com.qxf.newsapp.base.BaseView;

/**
 * Created by Administrator on 2017/9/15.
 */

public interface MainContract {

    interface Present extends BasePresenter {
    }

    interface View extends BaseView<Present> {
    }
}
