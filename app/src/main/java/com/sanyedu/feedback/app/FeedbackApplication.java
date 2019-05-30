package com.sanyedu.feedback.app;

import android.app.Application;

import com.sanyedu.feedback.imageloader.ImageLoader;
import com.sanyedu.feedback.imageloader.PicassoLoader;
import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.share.SpHelper;

public class FeedbackApplication extends Application {

    public static FeedbackApplication gApp;
    public static SpHelper spHelper;

    @Override
    public void onCreate() {
        SanyLogs.d("start app..");
        super.onCreate();
        gApp = this;
        //初始化图片库
        ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader());

    }

    public static final FeedbackApplication getApp(){
        return gApp;
    }
}
