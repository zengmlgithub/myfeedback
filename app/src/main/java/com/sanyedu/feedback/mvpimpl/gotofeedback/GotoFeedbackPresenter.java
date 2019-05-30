package com.sanyedu.feedback.mvpimpl.gotofeedback;

import android.support.annotation.NonNull;

import com.sanyedu.feedback.mvp.BasePresenter;
import com.sanyedu.feedback.mvp.IBaseView;

public class GotoFeedbackPresenter extends BasePresenter implements GotoFeedbackContacts.IGoToFeedbackPresenter {
    public GotoFeedbackPresenter(@NonNull IBaseView view) {
        super(view);
    }
}
