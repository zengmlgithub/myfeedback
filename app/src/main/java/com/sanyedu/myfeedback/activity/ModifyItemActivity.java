package com.sanyedu.myfeedback.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.MyBaseQuickAdapter;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.DetailedList;
import com.sanyedu.myfeedback.mvp.IBaseXPresenter;
import com.sanyedu.myfeedback.utils.ConstantUtil;
import com.sanyedu.myfeedback.utils.StartUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by zengmaolin
 * 显示所有的名字
 */
public class ModifyItemActivity extends SanyBaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.name_tv)
    TextView nameTv;

    @BindView(R.id.long_ago_tv)
    TextView longAgoTv;//登录时间

    @BindView(R.id.content_tv)
    TextView contentTv;

    @OnClick(R.id.goback_ib)
    public void onGoback(){
        finish();
    }

    DetailedList detailBean;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initDetail();
        initViews();
        initRecycleView();
    }

    private void initRecycleView() {
        if(detailBean != null){
            final List<String> photoPaths = getPhotoPaths(detailBean);
            printList(photoPaths);
            BaseQuickAdapter adapter = new MyBaseQuickAdapter(ModifyItemActivity.this,R.layout.item_modify_item,photoPaths);
//            LinearLayoutManager manager = new LinearLayoutManager(this);
//            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                    SanyLogs.i("you click:" + photoPaths.get(position));
                    StartUtils.startActivity(ModifyItemActivity.this,PhotoActivity.class,photoPaths.get(position));
                }
            });
        }
    }

    private List<String> getPhotoPaths(DetailedList detailBean) {
        List<String> paths = new ArrayList<>();
        paths.add(detailBean.getFeedbackFilea());
        paths.add(detailBean.getFeedbackFileb());
        paths.add(detailBean.getFeedbackFilec());
        return paths;
    }

    private void initViews() {
        if(detailBean != null){
            nameTv.setText(detailBean.getFeedbackPername());
            longAgoTv.setText(detailBean.getFeedbackTime());
            contentTv.setText(detailBean.getFeedbackContent());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_item;
    }

    @Override
    public IBaseXPresenter onBindPresenter() {
        return null;
    }

    private void initDetail() {
        Intent intent = getIntent();
        if (intent != null) {
            detailBean = (DetailedList) intent.getSerializableExtra(ConstantUtil.OBJ);
        }
    }

    private void printList(List<String> beans){
        for(String path:beans){
            SanyLogs.i(path);
        }
    }
}
