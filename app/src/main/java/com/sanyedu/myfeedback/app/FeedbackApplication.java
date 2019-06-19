package com.sanyedu.myfeedback.app;

import android.app.Application;

import com.sanyedu.myfeedback.imageloader.ImageLoader;
import com.sanyedu.myfeedback.imageloader.PicassoLoader;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.share.SpHelper;
import me.jessyan.autosize.AutoSizeConfig;

public class FeedbackApplication extends Application {

    public static FeedbackApplication gApp;
    public static SpHelper spHelper;

    @Override
    public void onCreate() {
        SanyLogs.d("start app..");
        super.onCreate();
        gApp = this;

        //增加自动适配方案
        AutoSizeConfig.getInstance().setCustomFragment(true);

        //初始化图片库
        ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader());


    }

    public static final FeedbackApplication getApp(){
        return gApp;
    }
}
