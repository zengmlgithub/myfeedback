package com.sanyedu.myfeedback.adapter;

import android.content.Context;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.utils.LoaderPictureUtils;

import java.util.List;

public class MyBaseQuickAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context context;
    public MyBaseQuickAdapter(Context context,int layoutResId, List<String> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_img);
        LoaderPictureUtils.load(context, item, (ImageView)helper.getView(R.id.iv_img), -1);
    }
}
