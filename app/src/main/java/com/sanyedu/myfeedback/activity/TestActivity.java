package com.sanyedu.myfeedback.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.NeedModifyAdapter;
import com.sanyedu.myfeedback.model.Records;


import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity  {

    @BindView(R.id.wait_el)
    RecyclerView listView;

    NeedModifyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_need_modify);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        adapter = new NeedModifyAdapter(this);
        listView.setHasFixedSize(true);
        listView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        listView.setLayoutManager(layout);

        List<Records> recordsList = createList();

        adapter.setRecordsList(recordsList);
    }

    private List<Records> createList() {
        List<Records> recordsList = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            Records records = new Records();
            records.setRectiStatus("1");
            records.setFeedbackContent("测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1");
            records.setFeedbackTitle("测试数据");
            records.setFeedbackDept("信息中心");
            records.setFeedbackAdress("A栋5楼");
            records.setFeedbackCreatetime("2019-01-04 13:00");
            records.setToResponsibledept("信息中心");
            records.setFeedbackPubtime("2019-01-07 13:00");
            recordsList.add(records);
        }
        return recordsList;
    }
}
