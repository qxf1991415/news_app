package com.qxf.newsapp.regist;

import android.app.Activity;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface RegistContract {

    interface View extends com.qxf.newsapp.base.BaseView<Presenter> {
    }

    interface Presenter extends com.qxf.newsapp.base.BasePresenter {
        void JumpToRegist(Class<? extends Activity> clazz);
    }
}
