package com.northcoders.record_shop_android_frontend.glideconfig;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class MyGlideModule extends AppGlideModule {
    // To log all Glide requests
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setLogLevel(Log.VERBOSE);
    }
}
