package com.qxf.newsapp.login;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Administrator on 2017/11/6.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginActivity activity;
    private LoginContract.View loginView;
    @Override
    public void start() {
    }

    public LoginPresenter(LoginContract.View loginView, Activity activity) {
        this.loginView = loginView;
        this.activity = (LoginActivity) activity;
    }

    @Override
    public void JumpToRegist(Class<? extends Activity> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }
}
