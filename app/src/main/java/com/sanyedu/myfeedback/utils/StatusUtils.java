package com.sanyedu.myfeedback.utils;

import android.text.TextUtils;
import com.sanyedu.myfeedback.R;

public class StatusUtils {
    public static String rectiStatus2String(String rectiStatus){
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

    /**
     * 状态码转成drawableid
     * @param rectiStatus
     * @return
     */
    public static int rectiStatus2DrawableId(String rectiStatus){
        if (TextUtils.isEmpty(rectiStatus)){
            return 0;
        }

        int drawableId = 0;
        switch (rectiStatus){
            case "1":
                drawableId = R.drawable.shape_half_ring_feedback_submmited;
                break;
            case "2":
                drawableId = R.drawable.shape_half_ring_feedback_rejected;
                break;
            case "3":
                drawableId = R.drawable.shape_half_ring_feedback_watting_modified;
                break;
            case "4":
                drawableId = R.drawable.shape_half_ring_feedback_modifing;
                break;
            case "5":
                drawableId = R.drawable.shape_half_ring_feedback_finished;
                break;
            case "6":
                drawableId = R.drawable.shape_half_ring_feedback_closed;
                break;
            default:
                break;
        }
        return drawableId;
    }

}
