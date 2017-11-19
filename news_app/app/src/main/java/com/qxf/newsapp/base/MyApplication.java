package com.qxf.newsapp.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.github.clientcloud.APPCloud;
import com.github.clientcloud.ApiServer;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.qxf.newsapp.utils.CommonUtils;
import com.qxf.newsapp.utils.CommonUtilsLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */

public class MyApplication extends Application {

    private static Context context;
    public static String currentUserNick = "";

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        context = getApplicationContext();
        if (getPackageName().equals(getProcessName(this, android.os.Process.myPid()))) {
            initCommonponent();
        }

        initHuanXin();
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

//    ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
//
//        private Toolbar toolbar;

//        @Override
//        public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
//            toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
//            if (toolbar != null) {
//                if (activity instanceof AppCompatActivity) {
//                    ((AppCompatActivity) activity).setSupportActionBar(toolbar);
//                    ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
//                }
//            }
//            if (activity.findViewById(R.id.toolbar_title) != null) {
//                ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
//            }
//            if (activity.findViewById(R.id.toolbar_back) != null) {
//                activity.findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        activity.onBackPressed();
//                    }
//                });
//            }
//        }
//
//        @Override
//        public void onActivityStarted(Activity activity) {
//
//        }
//
//        @Override
//        public void onActivityResumed(Activity activity) {
//
//        }
//
//        @Override
//        public void onActivityPaused(Activity activity) {
//
//        }
//
//        @Override
//        public void onActivityStopped(Activity activity) {
//
//        }
//
//        @Override
//        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//        }
//
//        @Override
//        public void onActivityDestroyed(Activity activity) {
//
//        }
//    };
}
