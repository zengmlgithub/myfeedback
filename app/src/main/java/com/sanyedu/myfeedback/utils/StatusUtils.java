package com.sanyedu.myfeedback.utils;

import android.text.TextUtils;

public class StatusUtils {
    public String rectiStatus2String(String rectiStatus){
        String  statusStr = "未知状态";
        if(TextUtils.isEmpty(rectiStatus)){
           return statusStr;
        }

        switch (rectiStatus){
            case "1":
                statusStr = "已提交";
                break;
            case "2":
                statusStr = "已驳回";
                break;
            case "3":
                statusStr = "待整改";
                break;
            case "4":
                statusStr = "整改中";
                break;
            case "5":
                statusStr = "已整改";
                break;
            case "6":
                statusStr = "已关闭";
                break;
                default:
                    statusStr = "未知状态";
                    break;
        }
        return statusStr;
    }
}
