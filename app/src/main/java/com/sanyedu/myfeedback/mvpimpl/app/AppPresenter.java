package com.sanyedu.myfeedback.mvpimpl.app;

import android.support.annotation.NonNull;

import com.sanyedu.myfeedback.mvp.BasePresenter;

public class AppPresenter extends BasePresenter<AppContacts.IAppUI> implements AppContacts.IAppPresenter {
    public AppPresenter(@NonNull AppContacts.IAppUI view) {
        super(view);
    }

    @Override
    public void getToken() {

    }
}
