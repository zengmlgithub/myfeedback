package com.sanyedu.myfeedback.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.activity.*;
import com.sanyedu.myfeedback.base.BaseFragment;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.TeacherBean;
import com.sanyedu.myfeedback.mvpimpl.mainmy.MainMyContacts;
import com.sanyedu.myfeedback.mvpimpl.mainmy.MainMyPresenter;
import com.sanyedu.myfeedback.share.SpHelper;
import com.sanyedu.myfeedback.utils.*;
import com.sanyedu.myfeedback.widget.CommonDialog;
import com.sanyedu.myfeedback.widget.PhotoPopupWindow;

import java.io.File;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static com.sanyedu.myfeedback.utils.ConstantUtil.*;

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

    private ImageButton settingIv;
    private RelativeLayout settingRl;
    private TextView modifywIb;
    private TextView logoutIb;

    private RelativeLayout emailRl;
    private RelativeLayout telRl;

    private TextView modifyTv;
    private TextView logoutTv;

    private Dialog dialog;

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
        initUserInfo();
        setListener();
        initFeedbackCount();
    }

    private void initFeedbackCount() {
        String id = UserInfoHelper.getPersonId();
        getPresenter().getMyInfoNum(id, FEEDBACK_MAIN);
        getPresenter().getMyInfoNum(id, MAIN_FEEDBACK);
    }

    private void initUserInfo() {
        TeacherBean userInfo = SpHelper.getObj(ConstantUtil.USERINFO);
        if(userInfo != null){
            /**
             *  username为帐号
             *  tename为名字
             */

            nameTv.setText(userInfo.getTeName());
            departTv.setText(userInfo.getTeDept() + "|" + userInfo.getTePosi());

            emailTv.setText(userInfo.getTeEmail());
            telTv.setText(userInfo.getTePhone());
            cardTv.setText(userInfo.getTeJobnum());
            posTv.setText(userInfo.getTeComp());
        }
    }

    private void setListener() {
        myFeedbackRl.setOnClickListener(this);
        feedbackMyRl.setOnClickListener(this);
        settingIv.setOnClickListener(this);

        headIv.setOnClickListener(this);
        settingIv.setOnClickListener(this);
        modifywIb.setOnClickListener(this);
        logoutIb.setOnClickListener(this);

        emailRl.setOnClickListener(this);
        telRl.setOnClickListener(this);
    }

    private void findViews(View view) {
        nameTv = view.findViewById(R.id.name_tv);
        departTv = view.findViewById(R.id.depart_tv);
        feedbackMyTv = view.findViewById(R.id.feedback_my_count_tv);
        myFeedbackTv = view.findViewById(R.id.my_feedback_count_tv);

        telTv = view.findViewById(R.id.tel_tv);
        emailTv = view.findViewById(R.id.email_tv);
        cardTv = view.findViewById(R.id.card_tv);
        posTv = view.findViewById(R.id.pos_tv);

        feedbackMyRl = view.findViewById(R.id.feedback_my_rl);
        myFeedbackRl = view.findViewById(R.id.my_feedback_rl);

        headIv = view.findViewById(R.id.head_iv);

        settingIv = view.findViewById(R.id.settings_iv);
        settingRl = view.findViewById(R.id.setting_rl);
        modifywIb = view.findViewById(R.id.modify_pwd_ib);
        logoutIb = view.findViewById(R.id.logout_ib);

        emailRl = view.findViewById(R.id.email_rl);
        telRl = view.findViewById(R.id.tel_rl);


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
    public void onClick(View v) {
        if (v.getId() == R.id.my_feedback_rl) { //我的反馈
            StartUtils.startActivity(getContext(), MyFeedbackActivity.class);
        } else if (v.getId() == R.id.feedback_my_rl) {  //反馈我的
            StartUtils.startActivity(getContext(), FeedbackMyActivity.class);
        } else if (v.getId() == headIv.getId()) {
            showPop();
        } else if (v.getId() == settingIv.getId()) {
            showSettings();
        } else if (v.getId() == modifywIb.getId()) {
            startModifyPwdActivity();
        } else if (v.getId() == logoutIb.getId()) {
            dialog = createDialog();
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } else if (v.getId() == emailRl.getId()) {
            modifyEmail();
        } else if (v.getId() == telRl.getId()) {
            modifyTel();
        }
    }

    private void modifyTel() {
        StartUtils.startActivity(getActivity(), ModifyTelActivity.class);
    }

    private void modifyEmail() {
        StartUtils.startActivity(getActivity(), ModifyEmailActivity.class);
    }


    //打开修改密码的activity
    private void startModifyPwdActivity() {
        StartUtils.startActivity(getActivity(), ModifyPwdActivity.class);
    }

    //显示悬浮按钮
    private void showSettings() {
        int visible = settingRl.getVisibility();
        if (visible != View.VISIBLE) {
            settingRl.setVisibility(View.VISIBLE);
        } else {
            settingRl.setVisibility(View.GONE);
        }
    }

    private void showPop() {
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
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        SanyLogs.e("before take photo" + pictureUri.toString());
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 回调成功
        SanyLogs.i("onActivityResult---->requestCode:" + requestCode + ",resultCode:" + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                // 切割
                case REQUEST_SMALL_IMAGE_CUTTING:
//                    Log.e(TAG,"before show");
                    SanyLogs.i("before show");
                    File cropFile = new File(FileUtils.getMyPetRootDirectory(), "crop.jpg");
                    Uri cropUri = Uri.fromFile(cropFile);
                    getPresenter().setPicToView(cropUri);
                    break;

                // 相册选取
                case REQUEST_IMAGE_GET:
                    Uri uri = PictureUtils.getImageUri(getContext(), data);
                    getPresenter().startPhotoZoom(uri);
                    break;

                // 拍照
                case REQUEST_IMAGE_CAPTURE:
                    File pictureFile = new File(FileUtils.getMyPetRootDirectory(), IMAGE_FILE_NAME);
                    Uri pictureUri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        pictureUri = FileProvider.getUriForFile(getContext(),
                                ConstantUtil.PROVIER_PATH, pictureFile);
//                        Log.e(TAG,"picURI="+pictureUri.toString());
                        SanyLogs.e("");
                    } else {
                        pictureUri = Uri.fromFile(pictureFile);
                    }
                    getPresenter().startPhotoZoom(pictureUri);
                    break;
                // 获取changeinfo销毁 后 回传的数据
//                case REQUEST_CHANGE_USER_NICK_NAME:
//                    String returnData = data.getStringExtra("data_return");
//                    InfoPrefs.setData(Constants.UserInfo.NAME,returnData);
//                    textView_user_nick_name.setText(InfoPrefs.getData(Constants.UserInfo.NAME));
//                    break;
                default:
            }
        } else {
//            Log.e(TAG,"result = "+resultCode+",request = "+requestCode);
            SanyLogs.e("result = " + resultCode + ",request = " + requestCode);
        }
    }

    @Override
    public InputStream openInputStream(Uri uri) {
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getContentResolver().openInputStream(uri);
        } catch (Exception e) {
            SanyLogs.e("e---》" + e.toString());
        }

        return inputStream;
    }

    @Override
    public void showHeadImage() {
        boolean isSdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);// 判断sdcard是否存在
        if (isSdCardExist) {

//            String path = InfoPrefs.getData(Constants.UserInfo.HEAD_IMAGE);// 获取图片路径
            String path = SpHelper.getString(HEAD_IMAGE);

            File file = new File(path);
            if (file.exists()) {
                Bitmap bm = BitmapFactory.decodeFile(path);
                // 将图片显示到ImageView中
                headIv.setImageBitmap(bm);
            } else {
                SanyLogs.i("no file");
//                headIv.setImageResource(R.drawable.huaji);
            }
        } else {
//            Log.e(TAG,"no SD card");
            SanyLogs.i();
//            headIv.setImageResource(R.drawable.huaji);
        }
    }

    @Override
    public void startActivityWithResult(Uri uri, Uri cropUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1); // 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300); // 输出图片大小
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);

        SanyLogs.i("cropUri = " + cropUri.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, REQUEST_SMALL_IMAGE_CUTTING);
    }

    @Override
    public void showMyFeedbackNumber(int count) {
        myFeedbackTv.setText(count + "");
    }

    @Override
    public void showFeedbackMyNumber(int count) {
        feedbackMyTv.setText(count + "");

    }




    private Dialog createDialog() {

        if (dialog == null) {
            dialog = new CommonDialog(getActivity(), R.style.sany_dialog, "您确定要注销此账号吗?", new CommonDialog.OnCloseListener() {

                @Override
                public void onPositive(Dialog dialog, boolean cancel) {
                    //此处将会清除sp内所有的信息
                    SanyLogs.i("进入到commonDialog");
                    SpHelper.clear();
                    dialog.dismiss();
                    StartUtils.startActivity(getContext(),LoginActivity.class);
                    getActivity().finish();
                }

                @Override
                public void onNevige(Dialog dialog, boolean confirm) {
                    dialog.dismiss();
                    //
                }
            }).setTitle("注销");
        }
        return dialog;
    }
}

