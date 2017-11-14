package com.qxf.newsapp.login;

import android.app.Activity;

import com.qxf.newsapp.base.BasePresenter;
import com.qxf.newsapp.base.BaseView;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface LoginContract {


    interface View extends BaseView<Presenter> {
        void getUserInput();
    }

    interface Presenter extends BasePresenter {
        void JumpToRegist(Class<? extends Activity> clazz, boolean finish);

        boolean checkUserInfo(String mUserName, String mPassword);
    }
}
