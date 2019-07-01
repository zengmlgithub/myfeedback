package com.sanyedu.myfeedback.app;

import android.app.Application;

import com.sanyedu.myfeedback.imageloader.ImageLoader;
import com.sanyedu.myfeedback.imageloader.PicassoLoader;
import com.tencent.bugly.crashreport.CrashReport;
import me.jessyan.autosize.AutoSizeConfig;

public class FeedbackApplication extends Application {

    public static FeedbackApplication gApp;

    @Override
    public void onCreate() {
        super.onCreate();
        gApp = this;

        //异常上报
        CrashReport.initCrashReport(getApplicationContext(), "f0f794e95c", false);

        //增加自动适配方案
        AutoSizeConfig.getInstance().setCustomFragment(true);

        //初始化图片库
        ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader());


    }

    public static final FeedbackApplication getApp(){
        return gApp;
    }
}
