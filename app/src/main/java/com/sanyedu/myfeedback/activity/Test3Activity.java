package com.sanyedu.myfeedback.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.NoticeAdapter;
import com.sanyedu.myfeedback.model.NoticeBean;

import java.util.ArrayList;
import java.util.List;

public class Test3Activity extends AppCompatActivity {
    @BindView(R.id.main_msg_rv)
    RecyclerView recyclerView;
    NoticeAdapter noticeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_msg);
        ButterKnife.bind(this);

        initData();

    }

    private void initData() {
        recyclerView.setHasFixedSize(true);
        noticeAdapter = new NoticeAdapter(this);
        recyclerView.setAdapter(noticeAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        setData();
    }

    private void setData() {
        List<NoticeBean> noticeList = new ArrayList<>();

        for(int i = 0; i < 100; i ++ ) {
            NoticeBean bean = new NoticeBean();
            bean.setContent("今天下午召开职工大会");
            bean.setCreatetime("2019-05-10 14:00");
            bean.setDept("全校所有人");
            bean.setTitle("关于召开职工大会的通知");
            bean.setPubName("行政中心");
            noticeList.add(bean);
        }

        noticeAdapter.setNoticeList(noticeList);
    }


}
