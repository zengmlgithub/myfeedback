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
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.Records;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.wait_el)
    RecyclerView listView;

    private NeedModifyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_need_modify);
        ButterKnife.bind(this);

        initViews();
        setData();
    }

    private void setData() {
        SanyLogs.i("setData~~~~~~");
        List<Records> recordsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Records records = new Records();
            records.setFeedbackAdress("A栋105");
            records.setFeedbackDept("信息中心");
            records.setToResponsibledept("信息中心");
            records.setFeedbackTitle("教学楼6楼天台上有鸟粪");
            records.setFeedbackContent("教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪教学楼6楼天台上有鸟粪");
            records.setRectiStatus( i%7 + "");
            recordsList.add(records);

        }
        adapter.setRecordsList(recordsList);
    }

    private void initViews() {
        adapter = new NeedModifyAdapter(this);
        listView.setHasFixedSize(true);
        listView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        listView.setLayoutManager(layout);
    }


}
