package com.sanyedu.myfeedback.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;


import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.adapter.FullyGridLayoutManager;
import com.sanyedu.myfeedback.adapter.GridImageAdapter;
import com.sanyedu.myfeedback.base.SanyBaseActivity;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.mvpimpl.gotofeedback.GotoFeedbackContacts;
import com.sanyedu.myfeedback.mvpimpl.gotofeedback.GotoFeedbackPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * 提交反馈的页面
 */
public class GotoFeedbackActivity extends SanyBaseActivity<GotoFeedbackPresenter> implements GotoFeedbackContacts.IGotoFeedbackUI, View.OnClickListener {

    private TextView gobackTv;
    private TextView commitTv;


    private int maxSelectNum = 3;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private RecyclerView mRecyclerView;
    private PopupWindow pop;

    private Spinner departSpinner;
    private Spinner personSpinner;

    private List<String> departList ;
    private List<String> personList;

    ArrayAdapter personAdapter;
    ArrayAdapter departAdapter;

    @Override
    public void onClick(View v) {
        if (v.getId() == gobackTv.getId()){
            finish();
        }else if (v.getId() == commitTv.getId()){
            //TODO:提交数据
        }
    }

    @Override
    protected void initData() {
        initPhoto();
        initDepart();
        initPerson();
    }

    private void initPhoto() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(GotoFeedbackActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(GotoFeedbackActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(GotoFeedbackActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

    }

    private void initPerson() {
        personList = new ArrayList<>();
        personList.add("请选择部门^");
        personList.add("兰胖子");
        personList.add("许棍棍");
        personList.add("李骑驴");


         personAdapter = new ArrayAdapter(this,R.layout.item_spinner,R.id.person_tv,personList);
        personSpinner.setAdapter(personAdapter);
    }

    private void initDepart() {
        departList = new ArrayList<>();
        departList.add("请选择责任人^");
        departList.add("信息中心");
        departList.add("学工处");
        departList.add("行政中心");

         departAdapter = new ArrayAdapter(this,R.layout.item_spinner,R.id.person_tv,departList);
        departSpinner.setAdapter(departAdapter);
    }

    @Override
    protected void findViews() {
        gobackTv = findViewById(R.id.goback_iv);
        commitTv = findViewById(R.id.submit_tv);
        mRecyclerView = findViewById(R.id.recycler);
        departSpinner = findViewById(R.id.policy_department_et);
        personSpinner = findViewById(R.id.policy_person_et);
    }

    @Override
    protected void setListeners() {
        gobackTv.setOnClickListener(this);
        commitTv.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_goto_feedback;
    }

    @Override
    public GotoFeedbackPresenter onBindPresenter() {
        return new GotoFeedbackPresenter(this);
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {

            //第一种方式，弹出选择和拍照的dialog
            showPop();

        }
    };

    private void showPop() {
        View bottomView = View.inflate(GotoFeedbackActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = (TextView) bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(GotoFeedbackActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(3)
                                .previewImage(true)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(GotoFeedbackActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }


    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SanyLogs.i( "onActivityResult~~~~~~requestCode:" + requestCode + ",resultCode:" + resultCode);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调

                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);

//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void setDepartList(List<String> departList) {
       //TODO:加载数据
    }

    @Override
    public void setperson(List<String> personList) {
        //TODO:加载数据
    }
}
