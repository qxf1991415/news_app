package com.qxf.newsapp.appli;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.github.clientcloud.APPCloud;
import com.github.clientcloud.ApiServer;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.qxf.newsapp.base.di.component.ApplicationComponent;
import com.qxf.newsapp.base.di.component.DaggerApplicationComponent;
import com.qxf.newsapp.base.di.module.ApplicationModule;
import com.qxf.newsapp.utils.CommonUtils;
import com.qxf.newsapp.utils.CommonUtilsLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */

public class MyApplication extends Application {

    private static Context context;
    public static String currentUserNick = "";
    private ApplicationComponent mApplicationComponent;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initApplicationComponent();
        if (getPackageName().equals(getProcessName(this, android.os.Process.myPid()))) {
            initCommonponent();
        }
        initHuanXin();
    }
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initHuanXin() {
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(getContext(), options);
    }

    private void initCommonponent() {
        initNetCloud();
    }

    private void initNetCloud() {
        CommonUtils.setCommonLoaderImpl(new CommonUtilsLoader());
        APPCloud.getInitializer().setContext(context).setEnablePresetApiServers(true).init();
        ApiServer.setDefaultConfig(ApiServer.Config.CONTENT_TYPE, "application/json; charset=UTF-8");
        ApiServer.setDefaultConfig(ApiServer.Config.ACCESS_TOKEN, "");
        ApiServer.setDefaultConfig(ApiServer.Config.APP_ID, CommonUtils.getAppId(this));
        ApiServer.setDefaultConfig(ApiServer.Config.APP_VERSION, CommonUtils.getVersionName(this));
        ApiServer.setDefaultConfig(ApiServer.Config.APP_KEY, CommonUtils.getAppKey(this));
        ApiServer.setDefaultConfig(ApiServer.Config.LANGUAGE, "zh-cn");
        ApiServer.setDefaultConfig(ApiServer.Config.TIMEZONE, "8");
    }

    public static String getProcessName(Context context, int pid) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = activityManager.getRunningAppProcesses();
        if (processInfoList == null || processInfoList.isEmpty()) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo info : processInfoList) {
            if (info.pid == pid) {
                return info.processName;
            }
        }
        return null;
    }
}
