package com.sanyedu.myfeedback.mvp;

import android.app.ProgressDialog;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.widget.SanyProgressDialog;

public abstract class BaseActivity<P extends IBasePresenter> extends BaseXActivity<P> implements IBaseView {
    // 加载进度框
    private ProgressDialog mProgressDialog;
    
    @Override
    public void showLoading(){
        //显示ProgressDialog
        mProgressDialog = new SanyProgressDialog(this, R.style.CustomProgressDialog);
        mProgressDialog.show();
    }
    
    @Override
    public void hideLoading(){
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        super.onDestroy();
    }
}