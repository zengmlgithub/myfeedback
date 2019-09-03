package com.sanyedu.myfeedback.update2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import com.sanyedu.myfeedback.widget.CommonDialog;

public class CommonProgressDialog extends Dialog {

    public CommonProgressDialog(@NonNull Context context) {
        super(context);
    }

    public CommonProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CommonProgressDialog(@NonNull Context context, boolean cancelable, @NonNull DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    //设置进度
    public void setIndeterminate(boolean b) {
    }

    //设置进度条样式
    public void setProgressStyle(int styleHorizontal) {
    }

    //设置最大值
    public void setMax(int i) {
    }

    //设置百分比
    public void setProgress(Integer progress) {
    }

    //设置显示的内容
    public void setMessage(String msg) {
    }
}
