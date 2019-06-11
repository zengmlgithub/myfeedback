package com.sanyedu.myfeedback.mvpimpl.gotofeedback;

import android.support.annotation.NonNull;

import android.text.TextUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.sanyedu.myfeedback.app.FeedbackApplication;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.*;
import com.sanyedu.myfeedback.mvp.BasePresenter;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GotoFeedbackPresenter extends BasePresenter<GotoFeedbackContacts.IGotoFeedbackUI> implements GotoFeedbackContacts.IGoToFeedbackPresenter {
    private List<String> pathList = new ArrayList<>();
    private List<String> serverPathList = new ArrayList<>();

    public GotoFeedbackPresenter(@NonNull GotoFeedbackContacts.IGotoFeedbackUI view) {
        super(view);
    }

    @Override
    public void getDepart() {
        String url = HttpUtil.getPort(HttpUtil.GET_ALL_DEPART_PORT);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<List<DepartBean>>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<DepartBean>> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<DepartBean> departList = response.getObj();
                                if (departList != null && departList.size() > 0) {
                                    getView().setDepartList(departList);
                                } else {
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }

    @Override
    public void getPersonOfDepart(String departId) {
        String url = HttpUtil.getPort(HttpUtil.GET_ONE_DEPART_TEACHER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.OneDepartTeacher.DEPART_ID, departId)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<List<PersonBean>>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<PersonBean>> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<PersonBean> personBeans = response.getObj();
                                if (personBeans != null && personBeans.size() > 0) {
                                    getView().setPersonList(personBeans);
                                } else {
                                    ToastUtil.showLongToast(response.getInfo());
                                }
                            }
                        }
                );
    }

    @Override
    public void postFeedbackToServer(FeedbackItem item) {
        if (item == null) {
            SanyLogs.i("item is null,return!");
            return;
        }

        SanyLogs.i(item.toString());

        String url = HttpUtil.getPort(HttpUtil.POST_FEEDBACK_TO_SERVER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_TITLE, item.getFeedbackTitle())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_ADDRESS, item.getFeedbackAddress())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_CONTENT, item.getFeedbackContent())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_DEPT, item.getFeedbackDept())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_ID, item.getFeedbackPersonid())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_NAME, item.getFeedbackPersonname())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_A, item.getFeedbackA())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_B, item.getFeedbackB())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_C, item.getFeedbackC())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBL_NAME, item.getToResponsiblename())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBLE_DEPT, item.getToResponsibledept())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBLE_ID, item.getToResponsibleid())
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                String feedbackId = response.getObj();
                                if (!TextUtils.isEmpty(feedbackId)) {
                                    ToastUtil.showLongToast("反馈上传成功！");
                                }
                            }
                        }
                );
    }


    private void postFile(final String fileName, final File file, final int i) {
        String url = HttpUtil.getPort(HttpUtil.UPLOAD_PHOTO_PORT);
        OkHttpUtils
                .post()
                .addFile("img", fileName, file)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                ToastUtil.showLongToast("上传失败");
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }
                                String path = response.getObj();
                                if (!TextUtils.isEmpty(path)) {
                                    SanyLogs.i("第" + i + "张图片上传成功");
                                }
                                //传一下张图片
                            }
                        }
                );
    }

    @Override
    public void postFiles(List<String> files) {
        if (files == null || files.size() <= 0) {
            SanyLogs.e("file is null,return");
            return;
        }

        pathList.clear();
        serverPathList.clear();
        pathList.addAll(files); //将所有的图片缓存

        if(pathList != null && pathList.size() > 0){
            String pathA = pathList.get(0);
            File fileA = new File(pathA);
            postFilesA(pathA, fileA, 0);
        }else{
            ToastUtil.showLongToast("请先拍照或选择已有图片");
        }
    }


    private File getCompressFile(File oldFile){
        File newFile = null;
        if(oldFile.exists() && oldFile.isFile()){
            newFile = CompressHelper.getDefault(FeedbackApplication.getApp()).compressToFile(oldFile);
        }
        return newFile;
    }

    private void postFilesA(final String fileName, final File file, final int i) {
        File newFile = getCompressFile(file);

        String url = HttpUtil.getPort(HttpUtil.UPLOAD_PHOTO_PORT);
        OkHttpUtils
                .post()
                .addFile("img", fileName, newFile)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                saveNullToServerList(i);
                                if(pathList != null && pathList.size() > 1) {
                                    String pathB = pathList.get(1);
                                    File fileB = new File(pathB);
                                    postFilesB(pathList.get(1), fileB, 1);
                                }else{
                                    startUploadData();
                                }
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    saveNullToServerList(i);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    saveNullToServerList(i);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    saveNullToServerList(i);
                                    return;
                                }
                                String path = response.getObj();
                                if (!TextUtils.isEmpty(path)) {
                                    SanyLogs.i("第" + i + "张图片上传成功");
                                    savePathToServerList(path,i);
                                } else {
                                    saveNullToServerList(i);
                                }
                                //传一下张图片
                                if(pathList != null && pathList.size() > 1) {
                                    String pathB = pathList.get(1);
                                    File fileB = new File(pathB);
                                    postFilesB(pathList.get(1), fileB, 1);
                                }else{
                                    startUploadData();
                                }

                            }
                        }
                );
    }

    private void postFilesB(String fileName, File fileB, final int i) {

        File newFile = getCompressFile(fileB);


        String url = HttpUtil.getPort(HttpUtil.UPLOAD_PHOTO_PORT);
        OkHttpUtils
                .post()
                .addFile("img", fileName, newFile)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());

                                saveNullToServerList(i);
                                if(pathList != null && pathList.size() > 2) {
                                    String pathC = pathList.get(2);
                                    File fileC = new File(pathC);
                                    postFilesC(pathList.get(2), fileC, 2);
                                }else{
                                    startUploadData();
                                }
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    saveNullToServerList(i);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    saveNullToServerList(i);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    saveNullToServerList(i);
                                    return;
                                }
                                String path = response.getObj();
                                if (!TextUtils.isEmpty(path)) {
                                    SanyLogs.i("第" + i + "张图片上传成功");
                                    savePathToServerList(path,i);
                                } else {
                                    saveNullToServerList(i);
                                }
                                //传一下张图片
                                if(pathList != null && pathList.size() > 2) {
                                    String pathC = pathList.get(2);
                                    File fileC = new File(pathC);
                                    postFilesC(pathList.get(2), fileC, 2);
                                }else{
                                    startUploadData();
                                }
                            }
                        }
                );
    }

    private void postFilesC(String fileName, File fileC, final int i) {

        File newFile = getCompressFile(fileC);

        String url = HttpUtil.getPort(HttpUtil.UPLOAD_PHOTO_PORT);
        OkHttpUtils
                .post()
                .addFile("img", fileName, newFile)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                saveNullToServerList(i);

                                //当pathC传完后，开始传其他的数据
                                startUploadData();
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    saveNullToServerList(i);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    saveNullToServerList(i);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    saveNullToServerList(i);
                                    return;
                                }
                                String path = response.getObj();
                                if (!TextUtils.isEmpty(path)) {
                                    SanyLogs.i("第" + i + "张图片上传成功");
                                    savePathToServerList(path,i);
                                } else {
                                    saveNullToServerList(i);
                                }
                                //当pathC传完后，开始传其他的数据
                                startUploadData();
                            }
                        }
                );
    }

    private void startUploadData(){
        if (hasPhoto()) {
            FeedbackItem feedbackItem = getView().getCurrentItem();
            if(serverPathList != null){
                switch (serverPathList.size()){
                    case 1:
                        feedbackItem.setFeedbackA(serverPathList.get(0));
                        postFeedbackToServer(feedbackItem);
                        break;
                    case 2:
                        feedbackItem.setFeedbackA(serverPathList.get(0));
                        feedbackItem.setFeedbackB(serverPathList.get(1));
                        postFeedbackToServer(feedbackItem);
                        break;
                    case 3:
                        feedbackItem.setFeedbackA(serverPathList.get(0));
                        feedbackItem.setFeedbackB(serverPathList.get(1));
                        feedbackItem.setFeedbackC(serverPathList.get(2));
                        postFeedbackToServer(feedbackItem);
                        break;
                    default:
                        break;
                }
            }

        } else {
            ToastUtil.showLongToast("图片上传失败，请重新上传");
        }
    }

    private boolean hasPhoto() {
        boolean result = false;
        if(serverPathList != null && serverPathList.size() > 0){
            for(int i = 0 ; i < serverPathList.size() ; i ++){
                if(!TextUtils.isEmpty(serverPathList.get(i))){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private void savePathToServerList(String path,int i) {
        ToastUtil.showLongToast("第" + (i+1) + "张图片上传成功");
        serverPathList.add(path);
    }

    private void saveNullToServerList(int i) {
        ToastUtil.showLongToast("第" + (i+1) + "张图片上传失败");
        serverPathList.add(null);
    }
}
