package com.sanyedu.feedback.mvpimpl.main;

import android.support.annotation.NonNull;

import com.sanyedu.feedback.mvp.BasePresenter;

public class MainPresenter extends BasePresenter<MainContacts.IMainUI>implements MainContacts.IMainPresenter {
    public MainPresenter(@NonNull MainContacts.IMainUI view) {
        super(view);
    }


    @Override
    public void cancel(Object tag) {

    }

    @Override
    public void cancelAll() {

    }

    @Override
    public boolean isViewAttach() {
        return false;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getImageParam() {
        String url = "http://ww2.sinaimg.cn/large/7a8aed7bgw1eutsd0pgiwj20go0p0djn.jpg";
        int width = 200;
        int height = 400;

        getView().setImage(url,width,height);
    }
}
