package com.sanyedu.myfeedback.mvpimpl.gotofeedback;

import android.support.annotation.NonNull;

import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.mvp.IBaseView;

public class GotoFeedbackPresenter extends BasePresenter implements GotoFeedbackContacts.IGoToFeedbackPresenter {
    public GotoFeedbackPresenter(@NonNull IBaseView view) {
        super(view);
    }
}
