package com.qxf.newsapp.login;

import com.qxf.newsapp.BasePresenter;
import com.qxf.newsapp.BaseView;

/**
 * Created by quanxiaofeng on 2017/11/5.
 */

public interface LoginContract {


    interface View extends BaseView<LoginContract.Present>{

    }
    interface Present extends BasePresenter{

    }
}
