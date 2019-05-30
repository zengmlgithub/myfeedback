package com.sanyedu.feedback.mvpimpl.hasmodified;

import android.support.annotation.NonNull;

import com.sanyedu.feedback.mvp.BasePresenter;

public class HasModifiedPresenter extends BasePresenter<HasModifiedContacts.IHasModifiedUI> implements HasModifiedContacts.IHasModifiedPresenter {
    public HasModifiedPresenter(@NonNull HasModifiedContacts.IHasModifiedUI view) {
        super(view);
    }
}
