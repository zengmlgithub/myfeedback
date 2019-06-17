package com.sanyedu.myfeedback.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sanyedu.myfeedback.imageloader.ImageLoader;
import com.sanyedu.myfeedback.imageloader.LoaderOptions;
import com.sanyedu.myfeedback.log.SanyLogs;


public class LoaderPictureUtils {
     static void loadImage(Context context, String path, float heigth, float width, int placeHolderId, ImageView imageView){
        if(TextUtils.isEmpty(path)){
//            throw new IllegalArgumentException("photo path is illegal.");
            SanyLogs.i("path is null,return!");
            return;
        }

        LoaderOptions optionsA = new LoaderOptions(path);

        if(placeHolderId != -1){
            optionsA.placeholder = context.getResources().getDrawable(placeHolderId);
        }

        optionsA.targetHeight = SystemUtils.dip2px(context,heigth);
        optionsA.targetWidth = SystemUtils.dip2px(context,width);

        ImageLoader.getInstance().loadOptions(optionsA);
    }

    public static void load(Context context, String url, ImageView iv, int placeHolderResId) {
        if (placeHolderResId == -1) {
            Glide.with(context)
                    .load(url)
                    .into(iv);
            return;
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(placeHolderResId);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(iv);
    }
}
