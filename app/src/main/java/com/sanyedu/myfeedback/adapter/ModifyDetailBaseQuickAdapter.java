package com.sanyedu.myfeedback.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.model.DetailedList;

public class ModifyDetailBaseQuickAdapter extends BaseQuickAdapter<DetailedList, BaseViewHolder> {

    public ModifyDetailBaseQuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailedList item) {
        if(item != null){
            helper.setText(R.id.name_tv,item.getFeedbackPername())
                    .setText(R.id.long_ago_tv,item.getFeedbackTime())
                    .setText(R.id.content_tv,item.getFeedbackContent());
        }

    }
}
