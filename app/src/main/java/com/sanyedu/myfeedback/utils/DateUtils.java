package com.sanyedu.myfeedback.utils;

import android.text.TextUtils;

public class DateUtils {
    public static String getDateString(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) return null;
        if (dateStr.length() >= 19) {
            return dateStr.substring(5, 16);
        } else {
            return dateStr;
        }
    }
}
