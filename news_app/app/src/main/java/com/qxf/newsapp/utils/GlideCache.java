package com.qxf.newsapp.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Administrator on 2017/11/17.
 */
public class GlideCache implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        String dir = "GlideCache";
        //设置缓存的大小为100M
        int cacheSize = 100 * 1000 * 1000;
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,dir, cacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
