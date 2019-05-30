package com.sanyedu.feedback.mvpimpl.app;

import android.support.annotation.NonNull;

import com.sanyedu.feedback.mvp.BasePresenter;

public class AppPresenter extends BasePresenter<AppContacts.IAppUI> implements AppContacts.IAppPresenter {
    public AppPresenter(@NonNull AppContacts.IAppUI view) {
        super(view);
    }

    @Override
    public void getToken() {

    }
}
