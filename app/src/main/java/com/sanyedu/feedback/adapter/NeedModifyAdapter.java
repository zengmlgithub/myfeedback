package com.sanyedu.feedback.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanyedu.feedback.R;
import com.sanyedu.feedback.log.SanyLogs;
import com.sanyedu.feedback.model.Records;

import java.util.ArrayList;
import java.util.List;

public class NeedModifyAdapter extends RecyclerView.Adapter<NeedModifyAdapter.MyViewHolder> {

    private Context context;
    private List<Records> recordsList = new ArrayList<>();
    private LayoutInflater inflater;
    private View view;

    public NeedModifyAdapter(Context context/*, LayoutInflater inflater*/) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = inflater.inflate(R.layout.item_waiting_modified_child,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Records records = recordsList.get(position);
//        SanyLogs.i("onBindViewHolder~~~records:" + records.toString());
        if (records != null){
//            holder.headIv.setBackground(null);
            holder.departTv.setText(records.getToResponsibledept());
            holder.addressTv.setText(records.getFeedbackAdress());
            holder.titleTv.setText(records.getFeedbackTitle());
            holder.contentTv.setText(records.getFeedbackContent());
            holder.feedbackDepartTv.setText("反馈部门：" + records.getFeedbackDept());

            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onClick(holder.itemView,position,records.getId());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView headIv;
        private TextView departTv;  //责任部门
        private TextView addressTv;
        private TextView titleTv;
        private TextView contentTv;
        private ImageView photo1Iv;
        private ImageView photo2Iv;
        private ImageView photo3Iv;
        private ImageView typeIv;
        private TextView feedbackDepartTv;//反馈部门

        private View itemView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            headIv = itemView.findViewById(R.id.head_iv);
            departTv = itemView.findViewById(R.id.depart_tv);
            addressTv = itemView.findViewById(R.id.address_tv);
            titleTv = itemView.findViewById(R.id.title_tv);
            contentTv = itemView.findViewById(R.id.detail_tv);

            photo1Iv = itemView.findViewById(R.id.photo1_iv);
            photo2Iv = itemView.findViewById(R.id.photo2_iv);
            photo3Iv = itemView.findViewById(R.id.photo3_iv);

            typeIv = itemView.findViewById(R.id.type_iv);

            feedbackDepartTv = itemView.findViewById(R.id.feedback_department_tv);
        }
    }

    public void setRecordsList(List<Records> recordsList) {
        SanyLogs.i("NeedModifyAdapter~~~~setRecordsList.size=" + recordsList.size());
        this.recordsList = recordsList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onClick(View view, int position, String id);
    }

    private OnItemClickListener onItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.onItemClickListener = itemClickListener;
    }
}
