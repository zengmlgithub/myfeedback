package com.sanyedu.myfeedback.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.log.SanyLogs;
import com.sanyedu.myfeedback.model.Records;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijianchang@yy.com on 2017/4/12.
 */

public class PullAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Records> datas = new ArrayList<>();

    private Context context;


    public PullAdapter(List<Records> datas, Context context, boolean hasMore) {
        this.datas = datas;
        this.context = context;

    }

    public PullAdapter(Context context){
        SanyLogs.i("pulladapter init~~~~");
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.item_waiting_modified_child, parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        SanyLogs.i("onBindViewHolder~~~");

            //设置部门头像
//            ((NormalHolder) holder).headCiv

            SanyLogs.i("onBindViewHolder~~~1111111");
            Records records = datas.get(position);

            ((NormalHolder) holder).addressTv.setText(records.getFeedbackAdress());
            ((NormalHolder) holder).titleTv.setText(records.getFeedbackTitle());
            ((NormalHolder) holder).contentTv.setText(records.getFeedbackContent());

            //设置图片
//            ((NormalHolder) holder).photo1Iv.setImageBitmap();
//            ((NormalHolder) holder).photo1Iv.setImageBitmap();
//            ((NormalHolder) holder).photo1Iv.setImageBitmap();
            ((NormalHolder) holder).toResponseTv.setText(records.getToResponsibledept());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    class NormalHolder extends RecyclerView.ViewHolder {


        private CircleImageView headCiv;
        private TextView departNameTv;
        private TextView addressTv;
        private TextView titleTv;
        private TextView contentTv;
        private ImageView photo1Iv;
        private ImageView photo2Iv;
        private ImageView photo3Iv;
        private TextView toResponseTv;

        public NormalHolder(View itemView) {
            super(itemView);
            headCiv = itemView.findViewById(R.id.head_iv);
            departNameTv = itemView.findViewById(R.id.depart_tv);
            addressTv = itemView.findViewById(R.id.address_tv);
            titleTv = itemView.findViewById(R.id.save_tv);
            contentTv = itemView.findViewById(R.id.detail_tv);
            photo1Iv = itemView.findViewById(R.id.photo1_iv);
            photo2Iv = itemView.findViewById(R.id.photo2_iv);
            photo3Iv = itemView.findViewById(R.id.photo3_iv);
            toResponseTv = itemView.findViewById(R.id.feedback_department_tv);
        }
    }

    public void setDatas(List<Records> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}
