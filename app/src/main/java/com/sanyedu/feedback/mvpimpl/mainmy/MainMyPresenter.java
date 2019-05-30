package com.sanyedu.feedback.mvpimpl.mainmy;

import android.support.annotation.NonNull;

import com.sanyedu.feedback.mvp.BasePresenter;
import com.sanyedu.feedback.utils.FileUtils;

public class MainMyPresenter extends BasePresenter<MainMyContacts.IMainMyUI> implements MainMyContacts.IMainMyPresenter {
    public MainMyPresenter(@NonNull MainMyContacts.IMainMyUI view) {
        super(view);
    }

    @Override
    public void mkdir() {
        FileUtils.mkdirRootDirectory();
    }
}
