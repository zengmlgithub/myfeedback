package com.sanyedu.myfeedback.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.model.NoticeBean;
import com.sanyedu.myfeedback.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {
    private Context context;
    private List<NoticeBean> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;


    public NoticeAdapter(Context context/*, List<NoticeBean> mList*/) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mList.size();

    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.item_msg, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view/*, listener*/);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        NoticeBean noticeBean = mList.get(position);
//        SanyLogs.i("onBindViewHolder:" + noticeBean.toString());
        if(noticeBean != null){
            holder.titleTv.setText(noticeBean.getTitle());

            String dateStr = DateUtils.getDateString(noticeBean.getCreatetime());
            holder.dateTv.setText(dateStr);
            holder.contentTv.setText(noticeBean.getContent());
            holder.personTv.setText(noticeBean.getPubName());
            final String id = noticeBean.getId();
            //单击
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //触发自定义监听的单击事件
                    onItemClickListener.onItemClick(holder.itemView,position,id);
                }
            });

        }else{
            holder.titleTv.setText("");
            holder.dateTv.setText("");
            holder.contentTv.setText("");
            holder.personTv.setText("");
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        TextView contentTv;
        ImageView typePhtoIv;
        TextView personTv;
        TextView dateTv;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            titleTv = itemView.findViewById(R.id.save_tv);
            contentTv = itemView.findViewById(R.id.content_tv);
            typePhtoIv = itemView.findViewById(R.id.feedback_iv);
            personTv = itemView.findViewById(R.id.person_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
        }
    }

    public void setNoticeList(List<NoticeBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position, String id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;
}
 

 
