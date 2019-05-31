package com.sanyedu.myfeedback.utils;

import android.text.TextUtils;

import com.sanyedu.myfeedback.log.SanyLogs;

public class TypeUtils {
    public static int str2Int(String str){
        int temp = 0;
        if(!TextUtils.isEmpty(str)){
            try{
                temp = Integer.valueOf(str);
            }catch (Exception e){
                SanyLogs.i("number error!");
            }
        }

        return temp;
    }
}
