package com.sanyedu.feedback.mvp;

import android.app.ProgressDialog;

public abstract class BaseActivity<P extends IBasePresenter> extends BaseXActivity<P> implements IBaseView {
    // 加载进度框
    private ProgressDialog mProgressDialog;
    
    @Override
    public void showLoading(){
        //TODO:显示loading
    }
    
    @Override
    public void hideLoading(){
       //TODO:隐藏loading
    }
    
    @Override
    public void showToast(String msg){
       //TODO:显示Toast
    }
    
    @Override
    protected void onDestroy() {
        hideLoading();
        super.onDestroy();
    }
}