package com.sanyedu.myfeedback.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sanyedu.myfeedback.R;
import com.sanyedu.myfeedback.model.DepartBean;
import com.sanyedu.myfeedback.model.PersonBean;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends BaseAdapter {

    private List<PersonBean> personList = new ArrayList<>();
    private LayoutInflater inflater;

    public PersonAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public DepartBean  getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView=inflater.inflate(R.layout.item_spinner, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.person_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PersonBean departBean = personList.get(position);
        if(departBean != null){
            viewHolder.name.setText(departBean.getTeName());
        }else{
            //TODO:如果没有数据的时候
        }
        return convertView;
    }

    class ViewHolder{
        TextView name;
    }

    public void setData(List<PersonBean> departList){
        this.personList = departList;
        notifyDataSetChanged();
    }
}
