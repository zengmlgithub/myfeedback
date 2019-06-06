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

public class NoticeTestActivity extends AppCompatActivity {

    @BindView(R.id.main_msg_rv)
    RecyclerView recyclerView;

    private NoticeAdapter noticeAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_msg);
        ButterKnife.bind(this);

        initViews();
        initData();
    }

    private void initData() {
        List<NoticeBean> noticeList = new ArrayList<>();

        for (int i = 0; i < 100; i ++) {
            NoticeBean notice = new NoticeBean();
            notice.setContent("请各部门做好6s工作，今天下午后勤部将进行各个地方的检查，如果没有做好，将扣分");
            notice.setCreatetime("2019-05-23 16:00");
            notice.setDept("后勤部");
            notice.setPubName("湖南三一工业职业技术学院学工部");
            notice.setTitle("关于做好6s的通知");

            noticeList.add(notice);
        }

        noticeAdapter.setNoticeList(noticeList);
    }

    private void initViews() {
        recyclerView.setHasFixedSize(true);
        noticeAdapter = new NoticeAdapter(this);
        recyclerView.setAdapter(noticeAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }


}
