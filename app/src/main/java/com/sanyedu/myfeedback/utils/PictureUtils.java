package com.sanyedu.myfeedback.utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import com.sanyedu.myfeedback.R;

import java.io.File;

public class PictureUtils {
    public static Uri getImageUri(Context context, Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(Build.VERSION.SDK_INT >= 19){
            if(DocumentsContract.isDocumentUri(context,uri)){
                String docId = DocumentsContract.getDocumentId(uri);
                if("com.android.providers.media.documents".equals(uri.getAuthority())){
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID+"="+id;
                    imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
                }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(context,contentUri,null);
                }
            }else if("content".equalsIgnoreCase(uri.getScheme())){
                imagePath = getImagePath(context,uri,null);
            }else if("file".equalsIgnoreCase(uri.getScheme())){
                imagePath = uri.getPath();
            }
        }else{
            uri= data.getData();
            imagePath = getImagePath(context,uri,null);
        }
        File file = new File(imagePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context,
                   ConstantUtil.PROVIER_PATH, file);
        } else {
            uri = Uri.fromFile(file);
        }

        return uri;
    }

    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    /**
     * 状态码转成drawableid
     * @param rectiStatus
     * @return
     */
    public static int rectiStatus2DrawableId(String rectiStatus){
        if (TextUtils.isEmpty(rectiStatus)){
            return 0;
        }

        int drawableId = 0;
        switch (rectiStatus){
            case "1":
                drawableId = R.drawable.shape_half_ring_feedback_submmited;
                break;
            case "2":
                drawableId = R.drawable.shape_half_ring_feedback_rejected;
                break;
            case "3":
                drawableId = R.drawable.shape_half_ring_feedback_watting_modified;
                break;
            case "4":
                drawableId = R.drawable.shape_half_ring_feedback_modifing;
                break;
            case "5":
                drawableId = R.drawable.shape_half_ring_feedback_finished;
                break;
            case "6":
                drawableId = R.drawable.shape_half_ring_feedback_closed;
                break;
            default:
                break;
        }

        return drawableId;
    }

}
