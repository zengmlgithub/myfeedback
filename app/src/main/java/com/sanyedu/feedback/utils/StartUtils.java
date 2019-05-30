package com.sanyedu.feedback.utils;

import android.content.Context;
import android.content.Intent;

import com.sanyedu.feedback.log.SanyLogs;

import static com.sanyedu.feedback.utils.HttpUtil.NoticeDetail.ID;

/**
 * 启动activity
 */
public class StartUtils {
    public static void startActivity(Context packageContext, Class<?> cls){
       try{
          packageContext.startActivity(new Intent(packageContext,cls));
       }catch (Exception e ){
           SanyLogs.e(e.toString());
       }
    }

    public static void startActivity(Context packageContext, Class<?> cls,String id){
        try {
            Intent intent = new Intent();
            intent.putExtra(ID, id);
            intent.setClass(packageContext, cls);
            packageContext.startActivity(intent);
        }catch (Exception e){
            SanyLogs.i(e.toString());
        }
    }
}
