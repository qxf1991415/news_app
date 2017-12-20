package com.qxf.newsapp.base.di.module;

import android.content.Context;

import com.qxf.newsapp.appli.MyApplication;
import com.qxf.newsapp.base.di.scope.ContextType;
import com.qxf.newsapp.base.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/12/20.
 */
@Module
public class ApplicationModule {
    MyApplication myApplication;

    public ApplicationModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @PerApp
    @ContextType("Application")
    public Context provideApplicationContext() {
        return myApplication.getApplicationContext();
    }
}
