package com.sanyedu.myfeedback.app;

import android.app.Application;

import com.sanyedu.myfeedback.imageloader.ImageLoader;
import com.sanyedu.myfeedback.imageloader.PicassoLoader;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.share.SpHelper;

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
