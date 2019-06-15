package com.sanyedu.myfeedback.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvp.IBaseXPresenter;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.StartUtils;

public class FirstActivity extends SanyBaseActivity {
    @BindView(R.id.next_btn)
    TextView skipButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        mHandler.post(myRunnale);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_first;
    }

    private boolean hasUserInfo() {
        TeacherBean userinfo = SpHelper.getObj(ConstantUtil.USERINFO);
        return userinfo  != null && userinfo.getTeName() != null;
    }

    private void goToNext(){
//        Class<?> clazz = hasUserInfo() ? MainActivity.class:LoginActivity.class;

        //TODO:test
        Class clazz = MainActivity.class;
        StartUtils.startActivity(FirstActivity.this,clazz);
        finish();
    }


    private int limitTime = 4;
    private Handler mHandler = new Handler();
    private Runnable myRunnale = new Runnable() {
        @Override
        public void run() {
            limitTime--;
            SanyLogs.i("current limitTime:" + limitTime);
            if(limitTime>0){
                mHandler.postDelayed(myRunnale,1000);
                String skipTxt = getResources().getString(R.string.skip_txt);
                String secondTxt = getResources().getString(R.string.second_txt);
                skipButton.setText(skipTxt + limitTime + "s");
            }else{
                mHandler.removeCallbacks(myRunnale);
                goToNext();

            }
        }
    };

    @Override
    public IBaseXPresenter onBindPresenter() {
        return null;
    }

    @OnClick(R.id.next_btn)
    public void goNext(){
        if (mHandler != null){
            mHandler.removeCallbacks(myRunnale);
        }
        goToNext();
    }
}
