package com.sanyedu.feedback.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;
import com.sanyedu.feedback.R;
import com.sanyedu.feedback.activity.FeedbackMyActivity;
import com.sanyedu.feedback.activity.MainActivity;
import com.sanyedu.feedback.activity.MyFeedbackActivity;
import com.sanyedu.feedback.base.BaseFragment;
import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.TeacherBean;
import com.sanyedu.feedback.model.UserInfo;
import com.sanyedu.feedback.mvpimpl.mainmy.MainMyContacts;
import com.sanyedu.feedback.mvpimpl.mainmy.MainMyPresenter;
import com.sanyedu.feedback.share.SpHelper;
import com.sanyedu.feedback.utils.ConstantUtil;
import com.sanyedu.feedback.utils.FileUtils;
import com.sanyedu.feedback.utils.StartUtils;
import com.sanyedu.feedback.utils.ToastUtil;
import com.sanyedu.feedback.widget.PhotoPopupWindow;

import java.io.File;

import static com.sanyedu.feedback.utils.ConstantUtil.*;

/**
 * 我的页面
 */
public class MainMyFragment extends BaseFragment<MainMyPresenter> implements MainMyContacts.IMainMyUI, View.OnClickListener {

    private TextView nameTv;
    private TextView departTv;

    private TextView myFeedbackTv; //我的反馈
    private TextView feedbackMyTv; //反馈我的

    private TextView telTv;
    private TextView emailTv;
    private TextView cardTv;
    private TextView posTv;

    private RelativeLayout myFeedbackRl;
    private RelativeLayout feedbackMyRl;

    private ImageView headIv;
    private PhotoPopupWindow mPhotoPopupWindow;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_my;
    }

    @Override
    protected void init(View view) {
        findViews(view);
        setData();
    }

    private void setData() {
        TeacherBean userInfo = SpHelper.getObj(ConstantUtil.USERINFO);

        nameTv.setText(userInfo.getUsername());
        departTv.setText(userInfo.getTeDept() + "|" + userInfo.getTePosi());

        emailTv.setText(userInfo.getTeEmail());
        telTv.setText(userInfo.getTePhone());
        cardTv.setText(userInfo.getTeJobnum());
        posTv.setText(userInfo.getTeComp());

//        myFeedbackTv.setText();
//        feedbackMyTv.setText();

        myFeedbackRl.setOnClickListener(this);
        feedbackMyRl.setOnClickListener(this);

        headIv.setOnClickListener(this);
    }

    private void findViews(View view){
        nameTv = view.findViewById(R.id.name_tv);
        departTv = view.findViewById(R.id.depart_tv);
        feedbackMyTv = view.findViewById(R.id.feedback_main_number_tv);
        myFeedbackTv = view.findViewById(R.id.main_fk_number_tv);

        telTv = view.findViewById(R.id.tel_tv);
        emailTv = view.findViewById(R.id.email_tv);
        cardTv = view.findViewById(R.id.card_tv);
        posTv = view.findViewById(R.id.pos_tv);

        feedbackMyRl = view.findViewById(R.id.fk_main_ll);
        myFeedbackRl = view.findViewById(R.id.main_fk_ll);

        headIv = view.findViewById(R.id.head_iv);
    }

    @Override
    public MainMyPresenter onBindPresenter() {
        return new MainMyPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_fk_ll){
            StartUtils.startActivity(getContext(), MyFeedbackActivity.class);
        }else if(v.getId() == R.id.fk_main_ll){  //反馈我的
            StartUtils.startActivity(getContext(), FeedbackMyActivity.class);
        }else if(v.getId() == headIv.getId()) {
            //TODO:弹出图片框
            //创建存放头像的文件夹
            getPresenter().mkdir();


            mPhotoPopupWindow = new PhotoPopupWindow(getActivity(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 文件权限申请
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // 权限还没有授予，进行申请
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200); // 申请的 requestCode 为 200
                    } else {
                        // 如果权限已经申请过，直接进行图片选择
                        mPhotoPopupWindow.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        // 判断系统中是否有处理该 Intent 的 Activity
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivityForResult(intent, REQUEST_IMAGE_GET);
                        } else {
                            Toast.makeText(getActivity(), "未找到图片查看器", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, new View.OnClickListener()
            {
                @Override
                public void onClick (View v){
                    // 拍照及文件权限申请
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // 权限还没有授予，进行申请
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 300); // 申请的 requestCode 为 300
                    } else {
                        // 权限已经申请，直接拍照
                        mPhotoPopupWindow.dismiss();
                        imageCapture();
                    }
                }
            });
            View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
            mPhotoPopupWindow.showAtLocation(rootView,
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    private void imageCapture() {
        Intent intent;
        Uri pictureUri;
        //getMyPetRootDirectory()得到的是Environment.getExternalStorageDirectory() + File.separator+"MyPet"
        //也就是我之前创建的存放头像的文件夹（目录）
        File pictureFile = new File(FileUtils.getMyPetRootDirectory(), IMAGE_FILE_NAME);
        // 判断当前系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //这一句非常重要
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //""中的内容是随意的，但最好用package名.provider名的形式，清晰明了
            pictureUri = FileProvider.getUriForFile(getActivity(),
                    ConstantUtil.PROVIER_PATH, pictureFile);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照,拍照的结果存到oictureUri对应的路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
//        Log.e(TAG,"before take photo"+pictureUri.toString());
        SanyLogs.e("before take photo"+pictureUri.toString());
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
}
