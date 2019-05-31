package com.sanyedu.myfeedback.mvpimpl.hasmodified;

import android.support.annotation.NonNull;

import com.sanyedu.myfeedback.mvp.BasePresenter;

public class HasModifiedPresenter extends BasePresenter<HasModifiedContacts.IHasModifiedUI> implements HasModifiedContacts.IHasModifiedPresenter {
    public HasModifiedPresenter(@NonNull HasModifiedContacts.IHasModifiedUI view) {
        super(view);
    }
}
