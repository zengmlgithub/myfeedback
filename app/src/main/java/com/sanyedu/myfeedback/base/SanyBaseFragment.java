package com.sanyedu.myfeedback.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.sanyedu.myfeedback.broadcastreceiver.NetBroadcastReceiver;
import com.sanyedu.myfeedback.mvp.BaseActivity;
import com.sanyedu.myfeedback.mvp.IBasePresenter;

public abstract class SanyBaseFragment<P extends IBasePresenter> extends BaseFragment<P> implements NetBroadcastReceiver.NetChangeListener {
    public static NetBroadcastReceiver.NetChangeListener netEvent;// 网络状态改变监听事件

    protected abstract void initData();
    protected abstract int getLayout();
}
