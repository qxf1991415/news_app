package com.qxf.newsapp.regist;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Administrator on 2017/11/6.
 */

public class RegistPresenter implements RegistContract.Presenter {

    private RegistActivity activity;
    private RegistContract.View registView;
    @Override
    public void start() {
    }

    public RegistPresenter(RegistContract.View registView, Activity activity) {
        this.registView = registView;
        this.activity = (RegistActivity) activity;
    }

    @Override
    public void JumpToRegist(Class<? extends Activity> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }
}
