package com.sanyedu.myfeedback.activity;

import android.content.Intent;

import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.mvp.IBaseXPresenter;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.LoaderPictureUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;

public class PhotoActivity extends SanyBaseActivity {

    @BindView(R.id.iv_photo)
    PhotoView photoView;

    private String photoString;

    @OnClick(R.id.goback_ib)
    public void onGoback(){
        finish();
    }
    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initIntent();
        initViews();
    }

    private void initViews() {
        LoaderPictureUtils.load(this,photoString,photoView,-1);
    }

    private void initIntent() {
        Intent intent = getIntent();
        if(intent != null){
            photoString = intent.getStringExtra(ConstantUtil.ID);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_photo;
    }

    @Override
    public IBaseXPresenter onBindPresenter() {
        return null;
    }
}
