package com.sanyedu.feedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.adapter.MainAdapter;
import com.sanyedu.feedback.base.SanyBaseActivity;
import com.sanyedu.feedback.fragment.MainFkFragment;
import com.sanyedu.feedback.fragment.NoticeFragment;
import com.sanyedu.feedback.fragment.MainMyFragment;
import com.sanyedu.feedback.model.Names;
import com.sanyedu.feedback.mvpimpl.test.TestContact;
import com.sanyedu.feedback.mvpimpl.test.TestPresenter;

import java.util.ArrayList;
import java.util.List;

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
