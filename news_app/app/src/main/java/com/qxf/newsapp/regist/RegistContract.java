package com.qxf.newsapp.regist;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.qxf.newsapp.base.BaseView;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface RegistContract {

    interface View extends BaseView<Presenter> {
        void start();

        void inintUserInfo();

        void initError();
    }

    interface Presenter extends com.qxf.newsapp.base.BasePresenter {
        void JumpToRegist(Class<? extends Activity> clazz);

        void chechInput(EditText editText, TextInputLayout inputLayout, String errorWarn);

        boolean checkUserInfo(String mUserName, String mPassword, String mRePassword);

        void registUser(String mUserName, String mPassword);

        void initHuanXinAccount(String mUserName, String mPassword);

    }
}
