package com.github.clientcloud.commons;

import android.content.Context;

import com.github.clientcloud.ApiServer;
import com.github.clientcloud.RetrofitIniter;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author quanxf
 * @description
 * @date 2017/9/19
 */

public class ScalarsConverterIniter implements RetrofitIniter {

    @Override
    public Retrofit.Builder initialize(Retrofit.Builder builder,
                                       ApiServer apiServer, Context context) {
        if (builder ==null){
            builder = new Retrofit.Builder();
        }
        return builder.addConverterFactory(ScalarsConverterFactory.create());
    }
}
