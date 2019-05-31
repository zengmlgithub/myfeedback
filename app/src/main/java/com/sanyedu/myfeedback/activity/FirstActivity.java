package com.sanyedu.myfeedback.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Button;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvp.IBaseXPresenter;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.StartUtils;

public class FirstActivity extends SanyBaseActivity {
    private Button skipButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_first);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                boolean success = hasUserInfo();
//                Class<?> clazz = success == true ? MainActivity.class:LoginActivity.class;
//                StartUtils.startActivity(FirstActivity.this,clazz);
//            }
//        },3000);
    }

    @Override
    protected void initData() {
        mHandler.post(myRunnale);
    }

    @Override
    protected void findViews() {
        skipButton = findViewById(R.id.next_btn);
    }

    @Override
    protected void setListeners() {

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
        Class<?> clazz = hasUserInfo() ? MainActivity.class:LoginActivity.class;
        StartUtils.startActivity(FirstActivity.this,clazz);
    }

//    private static final int MSG_CODE = 0;
    private int limitTime = 4;
    private Handler mHandler = new Handler();
    private Runnable myRunnale = new Runnable() {
        @Override
        public void run() {
            limitTime--;
            SanyLogs.i("current limitTime:" + limitTime);
            if(limitTime>0){
                mHandler.postDelayed(myRunnale,1000);
//                limitTime.setText("剩余"+time+"s");
                skipButton.setText("剩余" + limitTime + "s");
            }else{
//                mTvChange.setText("完毕");
                mHandler.removeCallbacks(myRunnale);
                goToNext();
            }
        }
    };

    @Override
    public IBaseXPresenter onBindPresenter() {
        return null;
    }
}
