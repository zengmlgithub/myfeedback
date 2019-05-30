package com.sanyedu.feedback.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.model.GroupBean;
import com.sanyedu.feedback.model.Records;
import com.sanyedu.feedback.model.WaitingModifiedBean;

import java.util.List;

public class WaitingModifiedAdapter extends BaseExpandableListAdapter {

   private Context mContext;
   private List<GroupBean> beanList;

    public WaitingModifiedAdapter(Context mContext) {
        this.mContext = mContext;
        this.beanList = beanList;
    }

    @Override
    public int getGroupCount() {
        return beanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return beanList.get(groupPosition).getBeanArrayList().size();
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return beanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
//        return beanList.get(groupPosition).getBeanArrayList().get(childPosition);
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder groupViewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_need_feedback_group,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle =convertView.findViewById(R.id.date_tv);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
//        groupViewHolder.tvTitle.setText(beanList.get(groupPosition).getDate());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waiting_modified_child,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.headIv = convertView.findViewById(R.id.head_iv);
            childViewHolder.departTv = convertView.findViewById(R.id.depart_tv);
            childViewHolder.addressTv = convertView.findViewById(R.id.address_iv);
            childViewHolder.titleTv = convertView.findViewById(R.id.title_tv);
            childViewHolder.detailTv = convertView.findViewById(R.id.detail_tv);
            childViewHolder.photo1Iv = convertView.findViewById(R.id.photo1_iv);
            childViewHolder.photo2Iv = convertView.findViewById(R.id.photo2_iv);
            childViewHolder.photo3Iv = convertView.findViewById(R.id.photo3_iv);
            childViewHolder.typeIv= convertView.findViewById(R.id.type_iv);
            childViewHolder.policyTv= convertView.findViewById(R.id.policy_department_tv);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        //TODO：此处与实际的内容对应
        childViewHolder.headIv.setBackgroundColor(Color.parseColor("#FF0000"));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
        TextView tvTitle;
    }

    static class ChildViewHolder {
        ImageView headIv;
        TextView departTv;
        ImageView addressTv;
        TextView titleTv;
        TextView detailTv;

        ImageView photo1Iv;
        ImageView photo2Iv;
        ImageView photo3Iv;

        ImageView typeIv;
        TextView policyTv;

    }

    public void setRecordses(List<Records> recordsList){
//        this.beanList = recordsList;
        notifyDataSetChanged();
    }
}
