package com.sanyedu.myfeedback.mvpimpl.UpdatePicture;

import android.text.TextUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.sanyedu.myfeedback.app.FeedbackApplication;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.BaseModel;
import com.sanyedu.myfeedback.model.BaseModelCallback;
import com.sanyedu.myfeedback.model.FeedbackItem;
import com.sanyedu.myfeedback.okhttp.OkHttpUtils;
import com.sanyedu.myfeedback.utils.ErrorUtils;
import com.sanyedu.myfeedback.utils.HttpUtil;
import com.sanyedu.myfeedback.utils.ToastUtil;
import okhttp3.Call;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UpdatePictureService {
    private List<String> pathList ;
    //服务器反馈过来的新的path
    private List<String> serverPathList;

    private UpdateFinishedListener updateFinishedListener;


    public UpdatePictureService(List<String> pathList, UpdateFinishedListener updateFinishedListener) {
        this.pathList = pathList;
        this.updateFinishedListener = updateFinishedListener;
        this.serverPathList = new ArrayList<>();
    }

    public interface UpdateFinishedListener {
        void updateFinished(List<String> serverPathList);
    }

    public void setUpdateFinishedListener(UpdateFinishedListener updateFinishedListener) {
        this.updateFinishedListener = updateFinishedListener;
    }

    public void postFiles() {
        if (pathList == null || pathList.size() <= 0) {
            SanyLogs.e("file is null,return");
            return;
        }
        serverPathList.clear();

        if (pathList != null && pathList.size() > 0) {
            String pathA = pathList.get(0);
            File fileA = new File(pathA);
            postFilesA(pathA, fileA, 0);
        } else {
            ToastUtil.showLongToast("请先拍照或选择已有图片");
        }
    }

    private File getCompressFile(File oldFile) {
        File newFile = null;
        if (oldFile.exists() && oldFile.isFile()) {
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
                                if (pathList != null && pathList.size() > 1) {
                                    String pathB = pathList.get(1);
                                    File fileB = new File(pathB);
                                    postFilesB(pathList.get(1), fileB, 1);
                                } else {
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
                                    savePathToServerList(path, i);
                                } else {
                                    saveNullToServerList(i);
                                }
                                //传一下张图片
                                if (pathList != null && pathList.size() > 1) {
                                    String pathB = pathList.get(1);
                                    File fileB = new File(pathB);
                                    postFilesB(pathList.get(1), fileB, 1);
                                } else {
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
                                if (pathList != null && pathList.size() > 2) {
                                    String pathC = pathList.get(2);
                                    File fileC = new File(pathC);
                                    postFilesC(pathList.get(2), fileC, 2);
                                } else {
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
                                    savePathToServerList(path, i);
                                } else {
                                    saveNullToServerList(i);
                                }
                                //传一下张图片
                                if (pathList != null && pathList.size() > 2) {
                                    String pathC = pathList.get(2);
                                    File fileC = new File(pathC);
                                    postFilesC(pathList.get(2), fileC, 2);
                                } else {
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
                                    savePathToServerList(path, i);
                                } else {
                                    saveNullToServerList(i);
                                }
                                startUploadData();
                            }
                        }
                );
    }

    private boolean hasPhoto() {
        boolean result = false;
        if (serverPathList != null && serverPathList.size() > 0) {
            for (int i = 0; i < serverPathList.size(); i++) {
                if (!TextUtils.isEmpty(serverPathList.get(i))) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private void savePathToServerList(String path, int i) {
        ToastUtil.showLongToast("第" + (i + 1) + "张图片上传成功");
        serverPathList.add(path);
    }

    private void saveNullToServerList(int i) {
        ToastUtil.showLongToast("第" + (i + 1) + "张图片上传失败");
        serverPathList.add(null);
    }

    private void startUploadData() {
        if (hasPhoto()) {
            if(updateFinishedListener != null){
                updateFinishedListener.updateFinished(serverPathList);
            }
        } else {
            updateFinishedListener.updateFinished(null);
        }
    }

    public static String getServicePathA(List<String> servicePathList){
        if(servicePathList == null || servicePathList.size() <= 0){
            return "";
        }else{
            return servicePathList.get(0);
        }
    }

    public static String getServicePathB(List<String> servicePathList){
        if(servicePathList == null || servicePathList.size() <= 1){
            return "";
        }else{
            return servicePathList.get(1);
        }
    }

    public static String getServicePathC(List<String> servicePathList){
        if(servicePathList == null || servicePathList.size() <= 2){
            return "";
        }else{
            return servicePathList.get(2);
        }
    }

}
