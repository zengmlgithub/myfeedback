package com.sanyedu.feedback.mvpimpl.mainmy;

import android.support.annotation.NonNull;

import com.sanyedu.feedback.mvp.BasePresenter;

public class MainMyPresenter extends BasePresenter<MainMyContacts.IMainMyUI> implements MainMyContacts.IMainMyPresenter {
    public MainMyPresenter(@NonNull MainMyContacts.IMainMyUI view) {
        super(view);
    }
}
