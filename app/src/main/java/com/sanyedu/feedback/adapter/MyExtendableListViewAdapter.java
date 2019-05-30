package com.sanyedu.feedback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.model.GroupBean;

import java.util.ArrayList;
import java.util.List;

public class MyExtendableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<GroupBean> groupList;


    public MyExtendableListViewAdapter(Context context){
        this.mContext = context;
        groupList = new ArrayList<>();
    }

    @Override
    // 获取分组的个数
    public int getGroupCount() {
        return groupList == null?0: groupList.size();
    }

    //获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return groupList.get(groupPosition).getItemList().size();
    }

    //        获取指定的分组数据
    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    //获取指定分组中的指定子选项数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).getItemList().get(childPosition);
    }

    //获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取子选项的ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    @Override
    public boolean hasStableIds() {
        return true;
    }
/**
 *
 * 获取显示指定组的视图对象
 *
 * @param groupPosition 组位置
 * @param isExpanded 该组是展开状态还是伸缩状态
 * @param convertView 重用已有的视图对象
 * @param parent 返回的视图对象始终依附于的视图组
  */
// 获取显示指定分组的视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_group,parent,false);
            groupViewHolder = new GroupViewHolder();

            //具体的group item 中控件
//            groupViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.label_group_normal);
//            convertView.setTag(groupViewHolder);

        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }

            //具体的Group item
//        groupViewHolder.tvTitle.setText(groupString[groupPosition]);
        return convertView;
    }
    /**
     *
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, View,
     *      ViewGroup)
     */

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_child,parent,false);
            childViewHolder = new ChildViewHolder();

//            childViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.expand_child);
//            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
//        childViewHolder.tvTitle.setText(childString[groupPosition][childPosition]);
        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        ImageView departIv;
        TextView departTv;
        TextView dateTv;
    }

    static class ChildViewHolder {
       ImageView feedbackIv;
       TextView feedbackTypeTv;
       TextView feedbackContentTv;
       TextView feedbackNumTv;
    }
}
