package com.sanyedu.myfeedback.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.MainAdapter;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.fragment.MainFkFragment;
import com.sanyedu.myfeedback.fragment.NoticeFragment;
import com.sanyedu.myfeedback.fragment.MainMyFragment;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.Names;
import com.sanyedu.myfeedback.mvpimpl.test.TestContact;
import com.sanyedu.myfeedback.mvpimpl.test.TestPresenter;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.FileUtils;
import com.sanyedu.myfeedback.utils.PictureUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.sanyedu.myfeedback.utils.ConstantUtil.*;

public class MainActivity extends SanyBaseActivity<TestPresenter> implements TestContact.ITestUI, View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> fragments;
    private MainAdapter mainAdapter;

    private ArrayList<String> titles;

    private int images[] = {R.drawable.msg_selector, R.drawable.feedback_selector, R.drawable.my_selector};


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new NoticeFragment());
        fragments.add(new MainFkFragment());
        fragments.add(new MainMyFragment());

        titles = new ArrayList<>();
        titles.add("消息");
        titles.add("今日反馈");
        titles.add("我的");


        mainAdapter = new MainAdapter(getSupportFragmentManager(), this, fragments, titles, images,tabLayout);

        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //设置自定义视图
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(mainAdapter.getTabView(i));
        }


    }

    @Override
    protected void findViews() {
        viewPager = findViewById(R.id.vp_content);
        tabLayout = findViewById(R.id.tablayout);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public TestPresenter onBindPresenter() {
        return null;
    }

    @Override
    public void setUserInfo(List<Names> nameList) {

    }
}
